<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<body>
    <%
        Technicians[] allTechnician = (Technicians[]) request.getAttribute("allTechnician");
        String start_date = (String) request.getAttribute("start_date");
        String end_date = (String) request.getAttribute("end_date");

    %>

    <h1>Technician_commissions</h1>
    <br>
    <h2><span><%= !start_date.isEmpty() ? start_date : "" %></span><%= !end_date.isEmpty() ? " => " + end_date : ""  %></h2>
    <form action="/Technician_commissions" method="post">
        <div class="form-group col-md-3">
            <label for="start_date">Start date</label>
            <input class="form-control" type="date" name="start_date" id="start_date">
        </div>
        <div class="form-group col-md-3">
            <label for="end_date">End date</label>
            <input class="form-control" type="date" name="end_date" id="end_date">
        </div>
        <div class="form-group col-md-3">
            <label for="technician">Technicians</label>
            <select name="technician_id" id="technician" class="form-control">
                <option value="0">-- choose --</option>
                <% for (int j = 0; j < allTechnician.length; j++) { %>
                <option value="<%= allTechnician[j].getId() %>"><%= allTechnician[j].getName() %>
                <% } %>
            </select>
        </div>
        <div class="form-group col-md-4">
            <button type="submit" class="btn btn-default col-md-4">Valider</button>
        </div>
    </form>

    <hr class="col-md-12">

    <div style="height : 75vh">
        <table class="table">
            <thead>
                <tr>
                    <th>Maintenance ID</th>
                    <th>Maintenance date</th>
                    <th>Technician</th>
                    <th>Total maintenance earnings</th>
                    <th>Technician commission (5%)</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Technician_commissions> data = (List<Technician_commissions>) request.getAttribute("Technician_commissions");
                    for (Technician_commissions item : data) {
                %>
                <tr>
                    <td><%= item.getMaintenance().getId() %></td>
                    <td><%= item.getMaintenance_date() %></td>
                    <td><%= item.getTechnician().getName() %></td>
                    <td><%= item.getTotal_maintenance_earnings() %></td>
                    <td><%= item.getTechnician_commission() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
