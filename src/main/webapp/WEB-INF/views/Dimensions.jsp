<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Dimensions[] all = (Dimensions[]) request.getAttribute("all");
        Dimensions currentDimensions = (Dimensions) request.getAttribute("currentDimensions");
        Machines_type[] allMachine_type = (Machines_type[]) request.getAttribute("allMachine_type");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Inch</th>
                    <th>Machines type</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getInch() %></td>
                    <td><%= all[i].getMachine_type() != null ? all[i].getMachine_type().getName() : "" %> </td>
                    <td>
                        <a href="/TraitDimensions/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Dimensions/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Dimensions" method="post">
                <input type="hidden" name="id" value="<%= currentDimensions != null ? currentDimensions.getId() : 0 %>">
                <% if (currentDimensions != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="inch">Inch</label>
                    <input type="number" name="inch" id="inch" class="form-control" value="<%= currentDimensions != null ? currentDimensions.getInch() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="machine_type">Machines type</label>
                    <select name="machine_type" id="machine_type" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allMachine_type.length; j++) { %>
                        <option value="<%= allMachine_type[j].getId() %>" <%= currentDimensions != null && currentDimensions.getMachine_type() != null && currentDimensions.getMachine_type().getId() == allMachine_type[j].getId() ? "selected" : "" %>><%= allMachine_type[j].getName() %>
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