<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        $(function () {
           $.ajax({
               type:"GET",
               url:"${pageContext.request.contextPath}/benchmarking/list/2018",
               success:function (msg) {
                   var company=[];
                   var salesAmount=[];
                   var chart = echarts.init(document.getElementById("box"));
                   var chart1 = echarts.init(document.getElementById("main"));

                   $(msg).each(function (index,item) {
                        company.push(item.companyName);
                        salesAmount.push(item.salesAmount);
                   });
                   var option = {
                       title: {
                           text: '2018年同行销售额比较',
                           left:'center',
                           top:'bottom'
                       },
                       legend: {
                           data:['销售额']
                       },
                       xAxis: {
                           data: company
                       },
                       yAxis: {
                           axisLabel:{
                               formatter:function (value,index) {
                                   return value+"(亿)";
                               }
                           }
                       },
                       series: [{
                           name: '销售额',
                           type: 'bar',
                           data: salesAmount
                       }]
                   };
                   var option1 = {
                       title: {
                           text: '2018年同行销售额比较'
                       },
                       legend: {
                           data:['销售额']
                       },
                       xAxis: {
                           data: company
                       },
                       yAxis: {},
                       series: [{
                           name: '销售额',
                           type: 'line',
                           data: salesAmount
                       }]
                   };
                   chart.setOption(option);
                   chart1.setOption(option1);
               }
           }) ;
        });
    </script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<div id="box" style="width:800px;height:600px;"></div>
<div id="main" style="width:800px;height:600px;"></div>

</body>
</html>