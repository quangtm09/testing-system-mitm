<%@ include file="/html/init.jsp"%>

<!-- Begin Page Content -->
<div id="container">
    <form>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <div id="lower">
            <input type="checkbox"><label class="check" for="checkbox">Keep me logged in</label>
            <input type="submit" value="Login">
        </div><!--/ lower-->
    </form>
</div><!--/ container-->
<!-- End Page Content -->