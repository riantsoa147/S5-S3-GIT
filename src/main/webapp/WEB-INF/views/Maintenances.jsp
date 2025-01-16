<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        String machines_type_id = (String) request.getAttribute("machines_type_id");
        String service_id = (String) request.getAttribute("service_id");
        String components_type_id = (String) request.getAttribute("components_type_id");
        String end_date = (String) request.getAttribute("end_date");
        Maintenances[] all = (Maintenances[]) request.getAttribute("all");
        Maintenances currentMaintenances = (Maintenances) request.getAttribute("currentMaintenances");
        Technicians[] allTechnician = (Technicians[]) request.getAttribute("allTechnician");
        Machines_clients_deposits[] allDeposit = (Machines_clients_deposits[]) request.getAttribute("allDeposit");
        Status[] allStatus = (Status[]) request.getAttribute("allStatus");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
        Machines_type[] allMachine_type = (Machines_type[]) request.getAttribute("allMachine_type");
        Services[] allService = (Services[]) request.getAttribute("allService");
    %>
    <div class="row">
        <div class="col-md-4">
            <form action="/Maintenances" method="post">
                <input type="hidden" name="id" value="<%= currentMaintenances != null ? currentMaintenances.getId() : 0 %>">
                <% if (currentMaintenances != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" name="price" id="price" class="form-control" value="<%= currentMaintenances != null ? currentMaintenances.getPrice() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="start_date">Start date</label>
                    <input type="datetime-local" name="start_date" id="start_date" class="form-control" value="<%= currentMaintenances != null ? currentMaintenances.getStart_date() : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %>"  step="1">
                </div>
                <div class="form-group">
                    <label for="end_date">End date</label>
                    <input type="datetime-local" name="end_date" id="end_date" class="form-control" value="<%= currentMaintenances != null ? currentMaintenances.getEnd_date() : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %>"  step="1">
                </div>
                <div class="form-group">
                    <label for="technician">Technicians</label>
                    <select name="technician" id="technician" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allTechnician.length; j++) { %>
                        <option value="<%= allTechnician[j].getId() %>" <%= currentMaintenances != null && currentMaintenances.getTechnician() != null && currentMaintenances.getTechnician().getId() == allTechnician[j].getId() ? "selected" : "" %>><%= allTechnician[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="deposit">Machines clients deposits</label>
                    <select name="deposit" id="deposit" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allDeposit.length; j++) { %>
                        <option value="<%= allDeposit[j].getId() %>" <%= currentMaintenances != null && currentMaintenances.getDeposit() != null && currentMaintenances.getDeposit().getId() == allDeposit[j].getId() ? "selected" : "" %>><%= allDeposit[j].getDeposit_date() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <select name="status" id="status" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allStatus.length; j++) { %>
                        <option value="<%= allStatus[j].getId() %>" <%= currentMaintenances != null && currentMaintenances.getStatus() != null && currentMaintenances.getStatus().getId() == allStatus[j].getId() ? "selected" : "" %>><%= allStatus[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
        <hr class="col-md-12">
        <div class="col-md-12 tableContainer " style="height: 100vh;">

            <form action="/Maintenances/filter" class="col-md-4" method="post">
                
                <input type="hidden" name="machines_type_id" value="<%= machines_type_id %>">
                <input type="hidden" name="service_id" value="<%= service_id %>">
                <input type="hidden" name="components_type_id" value="<%= components_type_id %>">

                <div class="form-group">
                    <label for="end_date">End date (Returning date)</label>
                    <input type="date" name="end_date" id="end_date" class="form-control">
                </div>
                <div  class="form-group">

                    <button type="submit" class="btn btn-default">Valider</button>
                </div>

            </form>

            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Price</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Technicians</th>
                    <th>Clients</th>
                    <th>Models</th>
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Machines types <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Maintenances/filter/0/<%= service_id %>/<%= components_type_id %>/<%= end_date %>">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < allMachine_type.length; j++) { %>
                                <li><a href="/Maintenances/filter/<%= allMachine_type[j].getId() %>/<%= service_id %>/<%= components_type_id %>/<%= end_date %>"><%= allMachine_type[j].getName() %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Maintenances/filter/<%= machines_type_id %>/0/<%= components_type_id %>/<%= end_date %>">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < allService.length; j++) { %>
                                <li><a href="/Maintenances/filter/<%= machines_type_id %>/<%= allService[j].getId() %>/<%= components_type_id %>/<%= end_date %>"><%= allService[j].getName() %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Components types <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Maintenances/filter/<%= machines_type_id %>/<%= service_id %>/0/<%= end_date %>">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < allComponent_type.length; j++) { %>
                                <li><a href="/Maintenances/filter/<%= machines_type_id %>/<%= service_id %>/<%= allComponent_type[j].getId() %>/<%= end_date %>"><%= allComponent_type[j].getName() %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getPrice() %></td>
                    <td><%= all[i].getStart_date() %></td>
                    <td><%= all[i].getEnd_date() %></td>
                    <td><%= all[i].getTechnician() != null ? all[i].getTechnician().getName() : "" %> </td>
                    <td><%= all[i].getDeposit() != null ? all[i].getDeposit().getClient().getName() : "" %> </td>
                    <td><%= all[i].getDeposit() != null ? all[i].getDeposit().getModels().getName() : "" %> </td>
                    <td><%= all[i].getDeposit() != null ? all[i].getDeposit().getModels().getMachine_type().getName() : "" %> </td>
                    <td><% for (int j = 0; j < all[i].getDetails().length ; j ++) { %>
                        <p><%= all[i].getDetails()[j].getService().getName() %></p>
                    <% } %></td>
                    <td><% for (int j = 0; j < all[i].getDetails().length ; j ++) { %>
                        <p><%= all[i].getDetails()[j].getComponent_type().getName() %></p>
                    <% } %></td>
                    <td><%= all[i].getStatus() != null ? all[i].getStatus().getName() : "" %> </td>
                    <td>
                        <a href="/TraitMaintenances/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Maintenances/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                    </td>
                </tr>
                <% } %>
            </table>
            <center><nav><ul class="pagination pagination-lg"></ul></nav></center>
            <script src="/assets/myjs/pagination.js"></script>
        </div>

        
</div>
    </div>

</body>
</html>