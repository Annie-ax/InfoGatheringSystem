
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>InfoGatheringSystem</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="/res/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/res/css/login.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="/res/lib/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="/res/lib/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<div class="container">
  <section class="loginBox1">
    <form action="/login" method="post">
      <div class="row">
        <div class="col-xs-7">
          <section class="left">
            <h1>LOGIN</h1>

            <p><input type="text" name="username" class="form-control" placeholder="用户名"/></p>

            <p><input type="password" name="password" class="form-control" placeholder="密码"/></p>
            <section>
              <section style="text-align: right;">
                <input type="submit" value=" 登录 " class="btn btn-primary">
              </section>
            </section>
          </section>
        </div>
        <div class="col-xs-5">
          <section>
            <h2>日志信息收集系统</h2>
          </section>
        </div>
      </div>
    </form>
  </section>
  <!-- /loginBox -->
</div>
<!-- /container -->
</body>
</html>
<script src="/res/js/jQuery-2.1.4.min.js"></script>
<script src="/res/js/bootstrap.min.js" type="text/javascript"></script>
