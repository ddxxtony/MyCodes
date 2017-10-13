<%-- 
    Document   : inicio
    Created on : 17/11/2016, 07:47:34 AM
    Author     : Alejandro Martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Inicio</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">  
     <script type="text/javascript">
            $(document).ready(function () {
                $("#hide").click(function () {
                    $("#element").hide();
                });
                $("#show").click(function () {
                    $("#element").show();
                });
            });
        </script>
        

</head>
<body id="page1">
	<div class="main-bg">
        <div class="bg">
            <!--==============================header=================================-->
            <header>
                <div class="main">
                    <div class="wrapper">
                        <h1><a href="index.jsp">logo</a></h1>
                        <div class="fright">
                            <div class="indent">
                                <span class="address">Universidad Autonoma de Aguascalientes, Mexico</span>
                                <span class="phone">Tel: +55 449 923 23 87</span>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
            </header>
            
            <!--==============================content================================-->
            
           
            <section id="content">
                <div style= " position: absolute; top: 40px; left: 390px;">
                    <h4>Agencia Automotriz</h4> 
                </div>
                
                <div style= " position: absolute; top: 40px; left: 220px; z-index: -1">
                    <a href="agencia.jsp">
                        <img src="images/agencia.png">
                    </a>    
                </div>
                
                <div style= " position: absolute; top: 40px; left: 910px;">
                    <h4>Taller Automotriz</h4> 
                </div>
                
                <div style= "position: absolute; top: 110px; left: 850px; ">
                    <a href="taller.jsp">                          
                        <img src="images/taller.png"> 
                    </a> 
                </div>
                                                                                                                        
            </section>
            
            <!--==============================footer=================================-->
            <footer>
                <div class="main" style="position: absolute; top: 600px; left: 200px;">
                    <span>ProCarElite &copy; 2016</span>
                    <a rel="nofollow" href="index.jsp" target="_blank">ProCarElite</a> by procarelite.com<br>
                </div>
            </footer>
        </div>
    </div>
	<script type="text/javascript"> Cufon.now(); </script>
    <script type="text/javascript">
		$(window).load(function() {
			$('.slider')._TMS({
				duration:1000,
				easing:'easeOutQuint',
				preset:'simpleFade',
				slideshow:7000,
				banners:false,
				pauseOnHover:true,
				pagination:false,
				pagNums:false,
				nextBu:'.next',
				prevBu:'.prev'
			});
		});
    </script>
</body>
</html>
<jsp:forward page="taller.jsp"/>
