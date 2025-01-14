<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Failing_components[] all = (Failing_components[]) request.getAttribute("all");
        Failing_components currentFailing_components = (Failing_components) request.getAttribute("currentFailing_components");
        Services[] allService = (Services[]) request.getAttribute("allService");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
        Diagnostics[] allDiagnostic = (Diagnostics[]) request.getAttribute("allDiagnostic");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Services</th>
                    <th>Components type</th>
                    <th>Diagnostics</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getService() != null ? all[i].getService().getName() : "" %> </td>
                    <td><%= all[i].getComponent_type() != null ? all[i].getComponent_type().getName() : "" %> </td>
                    <td><%= all[i].getDiagnostic() != null ? "<p>" + all[i].getDiagnostic().getDeposit().getClient().getName() + "</p> <p>" + all[i].getDiagnostic().getDeposit().getModels().getName() + " " + all[i].getDiagnostic().getDeposit().getDimension().getInch() + "\"</p>"  : "" %> </td>
                    <td>
                        <a href="/TraitFailing_components/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Failing_components/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                    </td>
                </tr>
                <% } %>
            </table>
            <center><nav><ul class="pagination pagination-lg"></ul></nav></center>
            <script src="/assets/myjs/pagination.js"></script>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-4">
            <form action="/Failing_components" method="post">
                <input type="hidden" name="id" value="<%= currentFailing_components != null ? currentFailing_components.getId() : 0 %>">
                <% if (currentFailing_components != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="service">Services</label>
                    <select name="service" id="service" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allService.length; j++) { %>
                        <option value="<%= allService[j].getId() %>" <%= currentFailing_components != null && currentFailing_components.getService() != null && currentFailing_components.getService().getId() == allService[j].getId() ? "selected" : "" %>><%= allService[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="component_type">Components type</label>
                    <select name="component_type" id="component_type" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent_type.length; j++) { %>
                        <option value="<%= allComponent_type[j].getId() %>" <%= currentFailing_components != null && currentFailing_components.getComponent_type() != null && currentFailing_components.getComponent_type().getId() == allComponent_type[j].getId() ? "selected" : "" %>><%= allComponent_type[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="diagnostic">Diagnostics</label>
                    <select name="diagnostic" id="diagnostic" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allDiagnostic.length; j++) { %>
                        <option value="<%= allDiagnostic[j].getId() %>" <%= currentFailing_components != null && currentFailing_components.getDiagnostic() != null && currentFailing_components.getDiagnostic().getId() == allDiagnostic[j].getId() ? "selected" : "" %>><%= allDiagnostic[j].getDeposit().getClient().getName() + " - " + allDiagnostic[j].getDeposit().getModels().getName() + " " + allDiagnostic[j].getDeposit().getDimension().getInch() + "\"" %>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>