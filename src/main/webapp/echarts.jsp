<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <title>Title</title>
</head>
<body>
    <div id="main" style="width: 600px;height: 400px;"></div>
    <script type="text/javascript">
        window.onload=function () {

            var names=[];
            var values=[];

            $.ajax({
               type:"GET",
               url:"${pageContext.request.contextPath}/echart/data",
               success:function (msg) {
                   for(key in msg){
                       names.push(key);
                       values.push(msg[key]);
                   }
                   var chart = echarts.init(document.getElementById("main"));
                   var option = {
                       title:{
                           text:'各操作系统手机销量情况',
                           subtext:'这是副文本标题',
                           top:'bottom',
                           left:'center',
                           textStyle:{
                               color:'#00ffff',
                               fontSize:30
                           }
                       },
                       tooltip:{},
                       legend:{
                           data:['销量']
                       },
                       xAxis:{
                           data:names
                       },
                       yAxis:{},
                       series:[{
                           name:'销量',
                           type:'bar',
                           data:values
                       }]
                   };
                   chart.setOption(option);
               }
            });
        }
    </script>
</body>
</html>
