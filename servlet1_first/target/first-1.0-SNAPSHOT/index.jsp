<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>

<div align="center">
    <h1>My Web Calculator
    </h1>
    <br/>
    <form action="calculate" method="post">
        <p>
            Input A  :&nbsp<input  type="number" name="a" required/>
        </p>
        <p>
            Input B  :&nbsp<input  type="number" name="b" required/>
        </p>
        <p>
            <input type="submit" value="RUN" />
        </p>
    </form>
</div>
</body>
</html>