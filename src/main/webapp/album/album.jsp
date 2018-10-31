<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        $('#ta').treegrid({
            url:'${pageContext.request.contextPath}/album/getSelect',
            idField:'id',
            treeField:'name',
            columns:[[
                {title:'名字',field:'name',width:180},
                {field:'url',title:'下载路径',width:60,align:'right'},
                {field:'size',title:'大小',width:80},
                {field:'duration',title:'时间',width:80}
            ]],
            method:"post",
            pagination:true,
            fit:true,
            fitColumns:true,
            toolbar:"#albumDiv"
        });
        /*详情框*/
        $('#album_xiangqing').dialog({
            title: '详情框',
            width: 300,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            closable:true,

        });
        //添加专辑框
        $('#album_ZhuanjiDiv').dialog({
            title: '添加专辑',
            width: 300,
            height: 300,
            closed: true,
            cache: false,
            modal: true,
            buttons:"#album_Zhuanji_xia",
            closable:true,

        });

        //添加章节框
        $('#album_AddDiv').dialog({
            title: '添加章节',
            width: 300,
            height: 300,
            closed: true,
            cache: false,
            modal: true,
            buttons:"#album_AddDiv_xia_a",
            closable:true,

        });

        $('#Db_yinyue').dialog({
            title: '播放栏',
            width: 300,
            height: 300,
            closed: true,
            cache: false,
            modal: true,
            closable:true,

        });

        //添加专辑下的提交
        $("#album_Zhuanji_xia_a").linkbutton({});
        $("#album_AddDiv_xia_a").linkbutton({});
        $("#ta").treegrid({
            onDblClickRow:function (row) {
                // console.log(row);
                DbStart(row);
            }
        })
    });

    //专家管理查看详细详情
    function album_Select() {
        var row = $("#ta").treegrid("getSelected");
        $.ajax({
            url:"${pageContext.request.contextPath}/album/selectParticulars",
            data:"id="+row.id,
            dataType:"json",
            type:"post",
            success:function (data) {
                console.log("${pageContext.request.contextPath}/album/"+data.data.coverImg+".jpg");

                if(data != null){
                    $("#album_xiangqing").dialog('open');
                    $("#album_xiangqing_name").val(data.data.name);
                    $("#album_xiangqing_coverImg").prop("src","${pageContext.request.contextPath}/album/"+data.data.coverImg);
                    $("#album_xiangqing_count").val(data.data.count);
                    $("#album_xiangqing_score").val(data.data.score);
                    $("#album_xiangqing_author").val(data.data.author);
                    $("#album_xiangqing_broadCast").val(data.data.broadCast);
                    $("#album_xiangqing_brief").val(data.data.brief);
                    $("#album_xiangqing_publishDate").val(data.data.publishDate);
                }else{
                    alert("请选中你要查看的专辑")
                }
            }
        })
    }

    /*添加专辑开始*/
    //添加专辑
    function album_AddZhuanji() {
        $("#album_ZhuanjiDiv").dialog('open');
    }
    
    function album_ZhuanjiTijao() {

        $("#album_ZhuanFrom").form('submit', {
            url:"${pageContext.request.contextPath}/album/addAlbum",
            onSubmit: function(){
                return $(this).form("validate");
            },
            success:function(data){
                alert(data)
                if(data == "true"){
                    // alert("进来了---")
                    $("#album_ZhuanjiDiv").dialog('close');
                }
                $("#ta").treegrid("reload");
            }

        });
    }
    
    /*添加专辑结束*/

    /*添加章节--开始*/
    function album_Add() {
        $("#album_AddDiv").dialog('open');
        var row = $("#ta").treegrid("getSelected");
        $("#abc").val(row.id);

    }
    
    function album_AddTijiao() {
        $("#album_AddDivFrom").form('submit', {
            url:"${pageContext.request.contextPath}/chapter/getInsert",
            onSubmit: function(){
                return $(this).form("validate");
            },
            success:function(data){
                // alert(data)
                if(data == "true"){
                    // alert("进来了---")
                    $("#album_AddDiv").dialog('close');
                }else{
                    alert("请选择专辑后添加")
                }
                $("#ta").treegrid("reload");
            }
        });
    }
    /*添加章节--结束*/

    /*双击播放音乐*/
    function DbStart(row) {
        console.log("${pageContext.request.contextPath}/yiyu/"+row.url);
        $("#sour").prop("src","${pageContext.request.contextPath}/yiyu/"+row.url);

        var a = $("#sour").prop("src");
        console.log(a);
        $("#Db_yinyue").dialog('open');
    }

    /*下载*/
    function album_Xiazai() {
        var row = $("#ta").treegrid("getSelected");
        alert(row.url);
        /*$.ajax({
            url:
            data:"url="+row.url,
            dataType:"json",
            type:"post",
            success:function (data) {
                alert(data);
            }
        })*/
        window.location.href="${pageContext.request.contextPath}/album/xiazai?url="+row.url;
    }

</script>

<table id="ta"> </table>

<%--专辑管理--开始--%>
<div id="albumDiv">
    <a class="easyui-linkbutton" onclick="album_Select()" data-options="iconCls:'icon-tip'">专辑详情</a>
    <a class="easyui-linkbutton" onclick="album_AddZhuanji()" data-options="iconCls:'icon-save'">添加专辑</a>
    <a class="easyui-linkbutton" onclick="album_Add()" data-options="iconCls:'icon-save'">添加章节</a>
    <a class="easyui-linkbutton" onclick="album_Xiazai()" data-options="iconCls:'icon-save'">下载</a>
</div>

<%--查看详情--%>
<div id="album_xiangqing">
    标题：<input id="album_xiangqing_name" type="text" disabled="disabled"><br><br>
    <%--插图：<input id="album_xiangqing_coverImg" type="text" disabled="disabled"><br><br>--%>
    插图:<img id="album_xiangqing_coverImg" src="" width="300px" height="150px"><br><br>
    章节数量：<input id="album_xiangqing_count" type="text" disabled="disabled"><br><br>
    得分：<input id="album_xiangqing_score" type="text" disabled="disabled"><br><br>
    作者：<input id="album_xiangqing_author" type="text" disabled="disabled"><br><br>
    波音人：<input id="album_xiangqing_broadCast" type="text" disabled="disabled"><br><br>
    描述：<input id="album_xiangqing_brief" type="text" disabled="disabled"><br><br>
    创建时间：<input id="album_xiangqing_publishDate" type="text" disabled="disabled"><br><br>
</div>

<%--添加专辑---开始--%>
<div id="album_ZhuanjiDiv">
    <form method="post" id="album_ZhuanFrom" enctype="multipart/form-data">
        标题:<input type="text" name="name"><br><br>
        插图:<input type="file" name="file"><br><br>
        作者:<input type="text" name="author"><br><br>
        波音人:<input type="text" name="disabled"><br><br>
        描述:<input type="text" name="disabled"><br><br>
    </form>
</div>
<div id="album_Zhuanji_xia">
    <a id="album_Zhuanji_xia_a" onclick="album_ZhuanjiTijao()">提交</a>
</div>
<%--添加专辑---结束--%>

<%--添加章节--开始--%>
<div id="album_AddDiv">
    <form method="post" id="album_AddDivFrom" enctype="multipart/form-data">
        <input type="hidden" name="aid" id="abc">
        章节名：<input type="text" name="name"><br><br>
        <input type="file" name="file"><br><br>
    </form>
    <a id="album_AddDiv_xia_a" onclick="album_AddTijiao()">提交</a>
</div>
<%--添加章节--结束--%>

<%--播放音乐---开始--%>
<div id="Db_yinyue">
    <audio controls="controls" src="" id="sour">
    <%--<source src="XXXX.ogg" type="audio/ogg">--%>
        <%--<source id="sour" src="" type="audio/mpeg">--%>
<%--        您的浏览器不支持audio标签--%>
    </audio>
</div>
<%--播放音乐---结束--%>



<%--专辑管理--结束--%>