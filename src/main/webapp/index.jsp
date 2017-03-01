
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><meta charset="UTF-8"/></head>
<body>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/TWAP.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<div class="container">

    <div class="row">

        <%@include file="WEB-INF/affichageDonnees.jsp" %>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="row">
            <div style="text-align: center;margin-top: 5px">

            </div>
        </div>
        <div class="col-lg-12">
            <div class="col-md-4">
                <label>TP TWAP</label> | <a href="https://github.com/VTFL/APP_TWAP">GitHub/APP_TWAP</a>
            </div>
            <div class="col-md-4" style="text-align: center;margin-bottom: 5px">
                <a href="https://twitter.com/share" class="twitter-share-button" data-text="du LOURD" data-size="large" data-hashtags="twap">Tweet</a> <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

            </div>
            <div class="col-md-4">
                <p class="muted pull-right" style="font-size:11px">Alline Florian - Defortescu Thibault - Patard Lucas - Pitel Valentin</p>
            </div>
        </div>
    </div>

</div>


<script type="text/javascript">
    <%@include file="js/index.js" %>
</script>
</body>
</html>
