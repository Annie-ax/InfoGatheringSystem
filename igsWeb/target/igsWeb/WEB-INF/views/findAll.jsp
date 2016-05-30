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
    <!-- iCheck -->
    <link rel="stylesheet" href="/res/plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="/res/plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="/res/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="/res/plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="/res/plugins/daterangepicker/daterangepicker-bs3.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="/res/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

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

        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">&nbsp;</h3>

                        <div class="box-tools">
                            <div class="input-group input-group-sm" style="width: 150px;">
                                <%--<input type="text" name="table_search" class="form-control pull-right"
                                       placeholder="Search">--%>

                                <%--<div class="input-group-btn">
                                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <tr>
                                <th>时间</th>
                                <th>来源</th>
                                <th>PID</th>
                                <th>等级</th>
                                <th>内容</th>
                            </tr>
                            <c:forEach items="${logList}" var="logInfo">
                                <tr>
                                    <td>${logInfo.happentime }</td>
                                    <td>${logInfo.source }</td>
                                    <td>${logInfo.pid }</td>
                                    <td>${logInfo.level }</td>
                                    <td>${logInfo.msg }</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="pageDiv">
                            <ul class="pagination" id="pageShow">
                                <li><a href="/pageQuery?currentPage=${page }&pageSum=${pageSum }&flag=0&tableName=${tableName }&pid=${pid }&level=${level }">&laquo;</a></li>
                                <c:forEach items="${pageList }" var="currentPage">
                                    <li><a href="/pageQuery?currentPage=${currentPage }&pageSum=${pageSum }&flag=1&tableName=${tableName }&pid=${pid }&level=${level }">${currentPage }</a></li>
                                </c:forEach>
                                <li><a href="/pageQuery?currentPage=${page }&pageSum=${pageSum }&flag=2&tableName=${tableName }&pid=${pid }&level=${level }">&raquo;</a></li>
                                <li><span>您当前在第${page }页</span></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>


    </div>

    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.1.0
        </div>
        <strong>Copyright &copy; 2014-2015 湖北工业大学</strong> All rights
        reserved.
    </footer>


    <!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="/res/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="/res/lib/jquery.ui/1.11.4/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/res/bootstrap/js/bootstrap.min.js"></script>
    <!-- Morris.js charts -->
    <script src="/res/lib/raphael/2.1.4/raphael-min.js"></script>
    <script src="/res/plugins/morris/morris.min.js"></script>
    <!-- Sparkline -->
    <script src="/res/plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="/res/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/res/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="/res/plugins/knob/jquery.knob.js"></script>
    <!-- daterangepicker -->
    <script src="/res/lib/moment.js/2.10.6/moment.min.js"></script>
    <script src="/res/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="/res/plugins/datepicker/bootstrap-datepicker.js"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="/res/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <!-- Slimscroll -->
    <script src="/res/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="/res/plugins/fastclick/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="/res/dist/js/app.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="/res/dist/js/pages/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="/res/dist/js/demo.js"></script>
</body>
</html>