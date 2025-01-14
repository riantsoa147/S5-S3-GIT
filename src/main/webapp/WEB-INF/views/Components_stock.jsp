<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Components_stock[] all = (Components_stock[]) request.getAttribute("all");
        Components_stock currentComponents_stock = (Components_stock) request.getAttribute("currentComponents_stock");
        Providers[] allProver = (Providers[]) request.getAttribute("allProver");
        Components[] allComponent = (Components[]) request.getAttribute("allComponent");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Entrance</th>
                    <th>Outflow</th>
                    <th>Stock date</th>
                    <th>Unit price</th>
                    <th>Providers</th>
                    <th>Components</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getEntrance() %></td>
                    <td><%= all[i].getOutflow() %></td>
                    <td><%= all[i].getStock_date() %></td>
                    <td><%= all[i].getUnit_price() %></td>
                    <td><%= all[i].getProver() != null ? all[i].getProver().getName() : "" %> </td>
                    <td><%= all[i].getComponent() != null ? all[i].getComponent().getName() : "" %> </td>
                    <td>
                        <a href="/TraitComponents_stock/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Components_stock/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Components_stock" method="post">
                <input type="hidden" name="id" value="<%= currentComponents_stock != null ? currentComponents_stock.getId() : 0 %>">
                <% if (currentComponents_stock != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="entrance">Entrance</label>
                    <input type="number" name="entrance" id="entrance" class="form-control" value="<%= currentComponents_stock != null ? currentComponents_stock.getEntrance() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="outflow">Outflow</label>
                    <input type="number" name="outflow" id="outflow" class="form-control" value="<%= currentComponents_stock != null ? currentComponents_stock.getOutflow() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="stock_date">Stock date</label>
                    <input type="date" name="stock_date" id="stock_date" class="form-control" value="<%= currentComponents_stock != null ? currentComponents_stock.getStock_date() : "" %>">
                </div>
                <div class="form-group">
                    <label for="unit_price">Unit price</label>
                    <input type="number" name="unit_price" id="unit_price" class="form-control" value="<%= currentComponents_stock != null ? currentComponents_stock.getUnit_price() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="prover">Providers</label>
                    <select name="prover" id="prover" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allProver.length; j++) { %>
                        <option value="<%= allProver[j].getId() %>" <%= currentComponents_stock != null && currentComponents_stock.getProver() != null && currentComponents_stock.getProver().getId() == allProver[j].getId() ? "selected" : "" %>><%= allProver[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="component">Components</label>
                    <select name="component" id="component" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent.length; j++) { %>
                        <option value="<%= allComponent[j].getId() %>" <%= currentComponents_stock != null && currentComponents_stock.getComponent() != null && currentComponents_stock.getComponent().getId() == allComponent[j].getId() ? "selected" : "" %>><%= allComponent[j].getName() %>
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