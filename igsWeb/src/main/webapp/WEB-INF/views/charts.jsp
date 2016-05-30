<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>InfoGatheringSystem</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="/res/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/res/lib/font-awesome/4.4.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="/res/lib/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/res/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="/res/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="/res/lib/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="/res/lib/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>I</b>GS</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Info</b>GatherSystem</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="/res/img/avatar5.png" class="user-image" alt="User Image">
              <span class="hidden-xs">${username}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="/res/img/avatar5.png" class="img-circle" alt="User Image">

              </li>
              <!-- Menu Body -->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-right">
                  <a href="/loginOut" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/res/img/avatar5.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${username}</p>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="treeview active">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>日志管理</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="<%=request.getContextPath()%>/getLogQueryPage"><i class="fa fa-circle-o"></i> 查询管理</a></li>
            <li><a href="<%=request.getContextPath()%>/getChartsPage"><i class="fa fa-circle-o"></i> 图表分析</a></li>
          </ul>
        </li>

      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">

    </section>

    <!-- Main content -->
    <section class="content">
      <input type="hidden" name="info" id="info" value="${info}">
      <input type="hidden" name="warning" id="warning" value="${warning}">
      <input type="hidden" name="error" id="error" value="${error}">
      <div class="row">
        <!-- general form elements -->
        <div class="box box-primary">
          <!-- /.box-header -->
          <!-- form start -->
          <form role="form" action="/showCharts" method="post">
            <div class="box-body">
              <div class="form-group">
                <label>日志源</label>
                <select class="form-control" name="sourceSelect" style="width: 30%;">
                  <c:forEach items="${sourceList}" var="sourceInfo">
                    <option>${sourceInfo.source}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
              <button type="submit" class="btn btn-primary" id="getChart">确定</button>
            </div>
          </form>
        </div>
        <!-- /.box -->
      </div>

      <div class="row">
        <div class="col-md-6">
          <!-- Bar chart -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-bar-chart-o"></i>

              <h3 class="box-title">柱形图</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <div id="bar-chart" style="height: 300px;"></div>
            </div>
            <!-- /.box-body-->
          </div>
          <!-- /.box -->
          </div>

          <!-- Donut chart -->
        <div class="col-md-6">
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-bar-chart-o"></i>

              <h3 class="box-title">饼状图</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              <div id="donut-chart" style="height: 300px;"></div>
            </div>
            <!-- /.box-body-->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->

  </div>

  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.0
    </div>
    <strong>Copyright &copy; 2014-2015 湖北工业大学</strong> All rights
    reserved.
  </footer>

</div>
  <!-- ./wrapper -->

  <script src="/res/plugins/jQuery/jQuery-2.1.4.min.js"></script>
  <!-- Bootstrap 3.3.5 -->
  <script src="/res/bootstrap/js/bootstrap.min.js"></script>
  <!-- FastClick -->
  <script src="/res/plugins/fastclick/fastclick.js"></script>
  <!-- AdminLTE App -->
  <script src="/res/dist/js/app.min.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="/res/dist/js/demo.js"></script>
  <!-- FLOT CHARTS -->
  <script src="/res/plugins/flot/jquery.flot.min.js"></script>
  <!-- FLOT RESIZE PLUGIN - allows the chart to redraw when the window is resized -->
  <script src="/res/plugins/flot/jquery.flot.resize.min.js"></script>
  <!-- FLOT PIE PLUGIN - also used to draw donut charts -->
  <script src="/res/plugins/flot/jquery.flot.pie.min.js"></script>
  <!-- FLOT CATEGORIES PLUGIN - Used to draw bar charts -->
  <script src="/res/plugins/flot/jquery.flot.categories.min.js"></script>

  <script src="/res/js/charts.js"></script>

</body>
</html>