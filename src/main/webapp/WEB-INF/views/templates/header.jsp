<%@page import="mg.s5s3.util.*"%>
<%@page import="mg.s5s3.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.time.format.*"%>
<%@ page import="java.lang.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Commin'IT app</title>
    <link rel="icon" href="/assets/img/commuintlogo.png" type="image/png">
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">My Project</a>
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarNav" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Machines configurations <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Brands">Brands</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Models">Models</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Dimensions">Dimensions</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Machines_type">Machines types</a></li>
                        <li role="separator" class="divider"></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Components configurations <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Providers">Providers</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Components">Components</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Components_type">Components types</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Components_recommandations">Components recommandations</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Components_stock">Components stocks</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Clients and Diagnostics <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Clients">Clients</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Machines_clients_deposits">Machines clients deposits</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Diagnostics">Diagnostics</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Failing_components">Failing components</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Technicians and Services <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Services">Services</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Technicians">Technicians</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Technician_commissions">Technician commissions</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Maintenances <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Status">Status</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Maintenances">Maintenances</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Maintenances_details">Maintenances details</a></li>
                    </ul>
                </li>
                
            </ul>
            <div class="navbar-right">
                <a class="btn btn-primary navbar-btn" href="#">Connexion</a>
            </div>
        </div>
    </div>
</nav>
