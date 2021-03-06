<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/app/constant.jsp" %>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
    <title>法律仲裁详细说明</title>
    <%@include file="/WEB-INF/views/app/meta-flex.jsp" %>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${mbStatic}/assets/css/public.css?v=<%=version%>" type="text/css">
    <link rel="stylesheet" href="${mbStatic}/assets/css/user.css?v=<%=version%>" type="text/css">
    <script src="${mbStatic}/assets/scripts/base/sea.js?v=<%=version%>21"></script>
    <script src="${mbStatic}/assets/scripts/base/flexible.js?v=<%=version%>"></script>
    <style>
        .infoTitle{
            height: 2rem;
            text-align: left;
            padding: 1rem 2rem;
            font-size: 1.2rem;
            font-weight: bold;
            background: url("${mbStatic}/assets/images/debt/columu@2x.png") 1rem no-repeat;
            background-size: .3rem;
            border-bottom: 1px solid RGB(223,223,223);
            overflow: hidden;
        }
        .box2 p span{
            height: 2.5rem;
            line-height: 2.5rem;
            text-align: center;
            display: inline-block;
            width: 11rem;
            border-bottom: 1px solid RGB(223,223,223);
            color: RGB(109,109,109);
            font-size: .9rem;
        }
        .box2 p span:nth-child(1){
            width: 6rem;
        }
        .box2 p span:nth-child(2){
            width: 9.5rem;
            border-left: 1px solid RGB(223,223,223);
            border-right: 1px solid RGB(223,223,223);
        }
        .box2 .dark span,.dark{
            color: #333;
        }
        .black{
            color: black;
            font-weight: bold!important;
        }
        .red{
            color:RGB(225,41,34)!important;
            font-weight: bold;!important;
        }
        .box2 .black span,.black{
            color: black;
        }

        .box3 p{
            border-bottom: 1px solid RGB(223,223,223);
            font-size: 1rem;
        }
        .box3 p span{
            display: inline-block;
            text-align: center;
            font-size: .9rem;
        }
        /*.box3 p:nth-child(1) span:first-child{*/
            /*float: left;*/
            /*width: 6rem;*/
            /*padding-top: 2rem;*/
        /*}*/
        /*.box3 p:nth-child(1) span:last-child{*/
            /*width: 20.5rem;*/
            /*border-left: 1px solid RGB(223,223,223);*/
        /*}*/
        .box3 p span:first-child{
            width: 5rem;
            height: 2.5rem;
            line-height: 2.5rem;
        }
        .box3 p span:last-child{
            width:  19.5rem;
            border-left: 1px solid RGB(223,223,223);
        }
        .box3 b{
            height: 2.5rem;
            line-height: 2.5rem;
            font-weight: normal;
            border-bottom: 1px solid RGB(223,223,223);
            width: 20rem;
            display: inline-block;
            text-align: left!important;
            padding-left: 1rem;
        }
        .box3 b:last-child{
            border-bottom: none;
        }
        .dis{
            float: left;
            padding-top: 2rem;
        }
        .rightSpan{
            height: 2.5rem;
            line-height: 2.5rem;
            padding-left: 1rem;
            text-align: left!important;
        }
        .box3 .specialLeft{
            height: 3.5rem;
            float: left;
            line-height: 3.5rem;
            padding-top: .7rem;
        }
        .box3 .specialRight{
            height: 3.5rem;
            line-height: 1.5rem;
            padding-top: .5rem;
            text-align: left;
            padding-left: 1rem;
        }

        .box4 p span{
            height: 3.5rem;
            line-height: 1.8rem;
            text-align: center;
            display: inline-block;
            width: 10rem;
            border-bottom: 1px solid RGB(223,223,223);
            color: RGB(109,109,109);
            font-size: .9rem;
            float: left;
        }
        .box4 p span:nth-child(1){
            width: 7.2rem;
        }
        .box4 p span:nth-child(2){
            width: 6.7rem;
            border-left: 1px solid RGB(223,223,223);
            border-right: 1px solid RGB(223,223,223);
        }
        .box4 p span:nth-child(3){
            width: 5rem;
            border-right: 1px solid RGB(223,223,223);
        }
        .box4 p span:nth-child(4){
            width: 7.258rem;
        }
        .box4 p span:nth-child(5){
            width: 5.6rem;
            border-left: 1px solid RGB(223,223,223);
            float: right;
        }
        .box4 .title span{
            color: RGB(23,23,23);
            height: 4rem;
            line-height: 2rem;
        }
        .box4 .title .center{
            line-height: 4rem!important;
        }
         .box3 .chirs{
            overflow: hidden;
         }
          .box3 .chirs span i{
            display: inline-block;
            width: 22rem;
            height: 4rem;
            line-height: 2rem;
            border-bottom: 1px solid RGB(223,223,223);
            font-weight: normal;
            text-align: left!important;
              padding-left: 1rem;
            }
           .box3 .chirs span i:last-child{
               border-bottom: 0px solid RGB(223,223,223);
               }
            .box3 .chirs span i b:first-child{
               display: inline-block;
               width: 5rem;
                height: 4rem;
                 line-height:4rem;
                }
            .box3 .chirs span i b:last-child{
                     display: inline-block;
                     width: 15rem;
                  height: 4rem;
                   line-height:4rem;
                   border-left: 1px solid RGB(223,223,223);
                   }

        .box5 p span{
            height: 3rem;
            line-height: 3rem;
            text-align: center;
            display: inline-block;
            width: 10rem;
            border-bottom: 1px solid RGB(223,223,223);
            color: RGB(109,109,109);
            font-size: .9rem;
            float: left;
        }
        .box5 p span:nth-child(1){
            width: 9rem;
        }
        .box5 p span:nth-child(2){
            width: 17.5rem;
            border-left: 1px solid RGB(223,223,223);
        }
        .box5 .title span{
            color: RGB(23,23,23);
            height: 3rem;
            line-height: 3rem;
        }

    </style>
</head>
<c:choose>
	<c:when test="${empty appPlatform}">
		<body>	
	</c:when>
	<c:otherwise>
		<body class="${isWeiXin? 'InWeixin':'InWebView'}" data-platform="${appPlatform }"  data-app="51jt">
	</c:otherwise>
</c:choose>
<article class="views">
    <section class="view">
        <div class="pages navbar-fixed toolbar-fixed">
            <div class="page">
                <div class="page-content user-main withdraw-main" style="background: white;padding-top: 0;">
                    <c:if test="${zcType eq '0'}">
	                    <p style="font-size: 1rem;padding: 1rem;">无忧借条是一款随手可用的电子借条手机应用。也是全国首款电子借条类工具，为用户提供经过CA认证、第三方数字存证、200多家公证处认可的规范化电子借条的平台。其上线的法律仲裁功能，是无忧借条平台针对处理逾期借条的特色功能，相对于法律诉讼来说，更专业、更快捷，同时与法律诉讼具有同等效力。</p>
	                    <p style="font-size: 1rem;padding-left: 1rem;">仲裁到底是什么？</p>
                    </c:if>
                    <h2 class="infoTitle">仲裁优势</h2>
                    <div class="box2">
                        <p ><span>类别</span><span>诉讼</span><span>仲裁</span></p>
                        <p class="dark"><span>周期</span><span>周期长</span><span class="red">最快15个工作日内</span></p>
                        <p class="dark"><span>费用</span><span>偏高</span><span class="red">较低</span></p>
                        <p class="dark"><span>送达</span><span>传票邮寄、公告送达</span><span class="red">电子邮箱送达有效</span></p>
                        <p class="dark"><span>审理</span><span>公开审理</span><span class="red">简易程序、书面审理</span></p>
                        <p class="dark"><span>结案</span><span>两轮终审</span><span class="red">一裁终局+代理强执</span></p>
                        <p class="dark"><span>效力</span><span class="red" style="width: 20.5rem;border-right: none;">同等法律效力</span></p>
                    </div>
                    <h2 class="infoTitle">一键线上仲裁</h2>
                    <div class="box3">
                        <p><span class="dis">申请条件</span><span ><b>借条金额≥100元</b><b>选择"<i class="red">通过仲裁方式</i>"完善协议，可申请仲裁</b></span></p>
                        <p><span>业务流程</span><span class="rightSpan">仲裁受理-立案/补材料-下裁决书-代理执行</span></p>
                        <p><span class="dis">仲裁优势</span><span ><b class="black">受理费低至40元每件，最快15个工作日出裁决书</b><b>无须出庭，直接拿证；同等法律效力</b></span></p>
                    </div>



                    <h2 class="infoTitle">仲裁收费标准</h2>
                    <div class="box4">
                        <p ><span>争议金额（元）</span><span>仲裁受理费（元）</span><span>仲裁处理费（元）</span><span>合计（元）</span></p>
                        <p class="title"><span class="center">≤1000</span><span class="center">40</span><span class="center">100</span><span class="center">140</span></p>
                        <p class="title"><span class="center">1001 - 50000</span><span>40 + 超过1000元部分的4%</span><span class="center">100</span><span>140 + 超过1000元部分的4%</span></p>

                        <p class="title"><span class="center">50001 - 100000</span><span>2000 + 超过5W元部分的3%</span><span class="center">100</span><span>2100 + 超过5W元部分的3%</span></p>

                        <p class="title"><span class="center">100001 - 200000</span><span>3500 + 超过10W元部分的2%</span><span class="center">100</span><span>3600 + 超过10W元部分的2%</span></p>

                        <p class="title"><span class="center">200001 - 500000</span><span>5500 + 超过10W元部分的1%</span><span class="center">100</span><span>5600 + 超过10W元部分的1%</span></p>
                        <p class="title"><span class="center">500001 - 1000000</span><span>8500 + 超过50W元部分的0.5%</span><span class="center">100</span><span>8600 + 超过50W元部分的0.5%</span></p>
                        <p class="title"><span class="center">1000001以上</span><span>11000 + 超过100W元部分的0.25%</span><span class="center">100</span><span>11100 + 超过100W元部分的0.25%</span></p>
                    </div>

                    <h2 class="infoTitle">委托代理执行</h2>
                    <div class="box3">
                        <p><span class="specialLeft">执行时间</span><span class="specialRight">欠款人在裁决书出具后10日内仍未还款，债权人申请执行</span></p>
                        <p><span class="specialLeft">执 行 方</span><span class="specialRight" style="line-height: 3rem;">专业律师团队</span></p>

                        <p><span class="specialLeft">执行流程</span><span class="specialRight">债权人申请执行—律师代理—法院执行—回款/上黑名单</span></p>
                        <p><span class="specialLeft">执行范围</span><span class="specialRight" style="line-height: 3rem;">覆盖率达全国95%以上的城市</span></p>
                        <p><span class="specialLeft">执行优势</span><span class="specialRight" style="line-height: 3rem;">全程委托律师办理，债权人轻松无忧</span></p>
                        <p class="chirs"><span class="dis">执行结果</span><span ><i><b style="height: 3rem;margin-bottom: 2rem; padding-left: 0;border-bottom: 0;">未履行还款</b><b style="line-height: 1rem;padding-left: .5rem;height: 4rem;">法院采取查封、扣押、冻结、划拨、拍卖、变卖等执行措施；申请人可申请将被执行人加入失信被执行人名单；</b></i><i><b style="padding-left:0;" >履行还款</b><b style="padding-left:.5rem;">执行案件终结</b></i></span></p>
                    </div>
                    <h2 class="infoTitle">执行收费标准</h2>
                    <div class="box5">
                        <p ><span>争议金额(元)</span><span>收费标准（元/件）</span></p>

                        <p class="title"><span>6000以下</span><span>200+回款20%</span></p>

                        <p class="title"><span>6000以上</span><span>费用需评估</span></p>


                    </div>
                    <c:if test="${zcType eq '0'}">
	                    <div style="font-size: .9rem;padding:1rem;">
	                        <p>&nbsp;</p>
	                        <p>说明：</p>
	                        <p style="text-indent: 1em">1、仲裁裁决一旦发出即发生法律效益，当事人应当履</p>
	                        <p style="text-indent: 2em">&nbsp;&nbsp;行仲裁裁决，否则权利人可以向法院申请强制执行，</p>
	                        <p style="text-indent: 2em">&nbsp;&nbsp;同时申请借款人身份信息登入法院失信被执行人名单；</p>
	                        <p style="text-indent: 1em">2、仲裁费由申请人预付，败诉方承担。</p>
	                    </div>
                    </c:if>
				</div>
            </div>
        </div>
    </section>
</article>

</body>
</html>
