<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Components_recommandations[] all = (Components_recommandations[]) request.getAttribute("all");
        Components_recommandations currentComponents_recommandations = (Components_recommandations) request.getAttribute("currentComponents_recommandations");
        Components[] allComponent = (Components[]) request.getAttribute("allComponent");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
        String[] months = (String[]) request.getAttribute("months");
        int[] years = (int[]) request.getAttribute("years");
        String year = (String) request.getAttribute("year");
        int month = Integer.parseInt((String) request.getAttribute("month"));
        String component_type_id = (String) request.getAttribute("component_type_id");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Date recommandation</th>
                    <th>Components</th>
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Components types <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Components_recommandations/filter/<%= year %>/<%= month %>/0">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < allComponent_type.length; j++) { %>
                                <li><a href="/Components_recommandations/filter/<%= year %>/<%= month %>/<%= allComponent_type[j].getId() %>"><%= allComponent_type[j].getName() %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>

                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Month <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Components_recommandations/filter/<%= year %>/0/<%= component_type_id %>">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < months.length; j++) { %>
                                <li><a href="/Components_recommandations/filter/<%= year %>/<%= j+1 %>/<%= component_type_id %>"><%= months[j] %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Year <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < years.length; j++) { %>
                                <li><a href="/Components_recommandations/filter/<%= years[j] %>/<%= month %>/<%= component_type_id %>"><%= years[j] %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    <th>Recommandations</th>

                    <th>Actions</th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() != 0 ? all[i].getId() : ""  %></td>
                    <td><%= all[i].getDate_recommandation() != null ? all[i].getDate_recommandation() : "" %></td>
                    <td><%= all[i].getComponent() != null ? all[i].getComponent().getName() : "" %> </td>
                    <td><%= all[i].getComponent() != null ? all[i].getComponent().getComponent_type().getName() : "" %> </td>
                    <td><%= month - 1 >= 0 ? months[month-1] : "" %></td>
                    <td></td>
                    <td><%= all[i].getRecommandations()!= 0 ? all[i].getRecommandations() : ""  %></td>
                    <td>
                        <a href="/TraitComponents_recommandations/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Components_recommandations/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Components_recommandations" method="post">
                <input type="hidden" name="id" value="<%= currentComponents_recommandations != null ? currentComponents_recommandations.getId() : 0 %>">
                <% if (currentComponents_recommandations != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="date_recommandation">Date recommandation</label>
                    <input type="date" name="date_recommandation" id="date_recommandation" class="form-control" value="<%= currentComponents_recommandations != null ? currentComponents_recommandations.getDate_recommandation() : "" %>">
                </div>
                <div class="form-group">
                    <label for="component">Components</label>
                    <select name="component" id="component" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent.length; j++) { %>
                        <option value="<%= allComponent[j].getId() %>" <%= currentComponents_recommandations != null && currentComponents_recommandations.getComponent() != null && currentComponents_recommandations.getComponent().getId() == allComponent[j].getId() ? "selected" : "" %>><%= allComponent[j].getName() %>
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