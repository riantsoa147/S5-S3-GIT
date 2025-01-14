<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Components_type[] all = (Components_type[]) request.getAttribute("all");
        Components_type currentComponents_type = (Components_type) request.getAttribute("currentComponents_type");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getName() %></td>
                    <td>
                        <a href="/TraitComponents_type/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Components_type/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Components_type" method="post">
                <input type="hidden" name="id" value="<%= currentComponents_type != null ? currentComponents_type.getId() : 0 %>">
                <% if (currentComponents_type != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= currentComponents_type != null ? currentComponents_type.getName() : "" %>">
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>