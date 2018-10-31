<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data: ['人数']
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    $.ajax({
        url: "${pageContext.request.contextPath}/tjzc",
        type: "post",
        dataType: "json",
        success: function (data) {

            myChart.setOption(option);
            myChart.setOption({
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: data
                }]
            });
        }
    })
</script>

<div id="main" style="width: 600px;height:400px;"></div>

