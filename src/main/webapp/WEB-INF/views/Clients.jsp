<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Clients[] all = (Clients[]) request.getAttribute("all");
        Clients currentClients = (Clients) request.getAttribute("currentClients");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Email</th>
                    <th>Phone number</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getEmail() %></td>
                    <td><%= all[i].getPhone_number() %></td>
                    <td><%= all[i].getName() %></td>
                    <td><%= all[i].getLocation() %></td>
                    <td>
                        <a href="/TraitClients/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Clients/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Clients" method="post">
                <input type="hidden" name="id" value="<%= currentClients != null ? currentClients.getId() : 0 %>">
                <% if (currentClients != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" class="form-control" value="<%= currentClients != null ? currentClients.getEmail() : "" %>">
                </div>
                <div class="form-group">
                    <label for="phone_number">Phone number</label>
                    <input type="text" name="phone_number" id="phone_number" class="form-control" value="<%= currentClients != null ? currentClients.getPhone_number() : "" %>">
                </div>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= currentClients != null ? currentClients.getName() : "" %>">
                </div>
                <div class="form-group">
                    <label for="location">Location</label>
                    <input type="text" name="location" id="location" class="form-control" value="<%= currentClients != null ? currentClients.getLocation() : "" %>">
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>