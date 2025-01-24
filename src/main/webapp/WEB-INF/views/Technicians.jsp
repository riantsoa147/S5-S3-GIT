<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Technicians[] all = (Technicians[]) request.getAttribute("all");
        Technicians currentTechnicians = (Technicians) request.getAttribute("currentTechnicians");
        Gender[] allGender = (Gender[]) request.getAttribute("allGender");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Salary</th>
                    <th>Gender</th>
                    <th>Commission</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getName() %></td>
                    <td><%= all[i].getSalary() %></td>
                    <td><%= all[i].getGender() != null ? all[i].getGender().getName() : "" %> </td>
                    <td><%= all[i].getCommission() %></td>
                    <td>
                        <a href="/TraitTechnicians/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Technicians/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Technicians" method="post">
                <input type="hidden" name="id" value="<%= currentTechnicians != null ? currentTechnicians.getId() : 0 %>">
                <% if (currentTechnicians != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= currentTechnicians != null ? currentTechnicians.getName() : "" %>">
                </div>
                <div class="form-group">
                    <label for="salary">Salary</label>
                    <input type="number" name="salary" id="salary" class="form-control" value="<%= currentTechnicians != null ? currentTechnicians.getSalary() : "" %>">
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <select name="gender" id="gender" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allGender.length; j++) { %>
                        <option value="<%= allGender[j].getId() %>" <%= currentTechnicians != null && currentTechnicians.getGender() != null && currentTechnicians.getGender().getId() == allGender[j].getId() ? "selected" : "" %>><%= allGender[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="commission">Commission</label>
                    <input type="number" name="commission" id="commission" class="form-control" value="<%= currentTechnicians != null ? currentTechnicians.getCommission() : "" %>">
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>