<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Config[] all = (Config[]) request.getAttribute("all");
        Config currentConfig = (Config) request.getAttribute("currentConfig");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Maximum</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getMaximum() %></td>
                    <td>
                        <a href="/TraitConfig/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Config/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Config" method="post">
                <input type="hidden" name="id" value="<%= currentConfig != null ? currentConfig.getId() : 0 %>">
                <% if (currentConfig != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="maximum">Maximum</label>
                    <input type="number" name="maximum" id="maximum" class="form-control" value="<%= currentConfig != null ? currentConfig.getMaximum() : "" %>">
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>