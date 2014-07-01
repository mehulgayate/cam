<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Registration Form</title>
  <link rel="stylesheet" href="/static/css/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>
  <div class="container">
    <section class="register">
      <h1>Register Company</h1>
      <form method="post" action="/company/register/add">
      <div class="reg_section personal_info">
      <h3>Company Information</h3>
      <input type="text" name="name" value="" placeholder="Company Username">
      <input type="text" name="email" value="" placeholder="Company E-mail Address">
      </div>
      <div class="reg_section password">
      <h3>Company Password</h3>
      <input type="password" name="password" value="" placeholder="Your Password">
      </div>
      <div class="reg_section password">
      <h3>Company Address</h3>
      <select name="country">
        <option value="">Egypt</option>
        <option value="">Palastine</option>
        <option value="">Syria</option>
        <option value="">Italy</option>
      </select>
      <textarea name="address" id="">Company Full Address</textarea>
      </div>
      <p class="terms">
        <label>
          
        </label>
      </p>
      <p class="submit"><input type="submit" name="commit" value="Sign Up"></p>
      </form>
    </section>
  </div>
</body>
</html>