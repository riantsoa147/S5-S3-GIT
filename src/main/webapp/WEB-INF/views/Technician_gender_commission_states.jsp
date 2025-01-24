<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<body>

    <h1>Technician gender commission states</h1>
    <form action="/Technician_gender_commission_states" method="post">
        <div class="form-group col-md-3">
            <label for="start_date">Start date</label>
            <input class="form-control" type="date" name="start_date" id="start_date">
        </div>
        <div class="form-group col-md-3">
            <label for="end_date">End date</label>
            <input class="form-control" type="date" name="end_date" id="end_date">
        </div>
        <hr class="col-md-12" style="visibility: hidden;">
        <div class="form-group col-md-3">
            <button type="submit" class="btn btn-default col-md-4">Valider</button>
        </div>
    </form>

    <table class="table">
        <thead>
            <tr>
                <th>Maintenance date</th>
                <th>Gender</th>
                <th>Sum</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Technician_gender_commission_states> data = (List<Technician_gender_commission_states>) request.getAttribute("Technician_gender_commission_states");
                for (Technician_gender_commission_states item : data) {
            %>
            <tr>
                <td><%= item.getMaintenance_date() %></td>
                <td><%= item.getGender().getName() %></td>
                <td><%= item.getSum() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
