<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/app/constant.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>借款知情告知书</title>
    <%@include file="/WEB-INF/views/app/meta-flex.jsp" %>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${mbStatic}/assets/css/public.css?v=<%=version%>" type="text/css">
    <link rel="stylesheet" href="${mbStatic}/assets/css/user.css?v=<%=version%>" type="text/css">
    <script src="${mbStatic}/assets/scripts/base/sea.js?v=<%=version%>21"></script>
    <script src="${mbStatic}/assets/scripts/base/flexible.js?v=<%=version%>"></script>
    <style>
        p{
           font-size: 1rem;
           line-height: 2rem;
           margin-bottom: 1rem;
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
                <div class="page-content user-main withdraw-main" style="background: white;padding:.5rem;">

                <p>尊敬的客户：</p>
                <p>很高兴为您提供服务！</p>
                <p>在您接受我公司（我公司包括我公司及其关联实体以及与我公司合作的第三方机构，以下同）服务的过程中，您应当知晓以下信息。若您不同意以下任一条款，请您切勿点击勾选或者点击下一步，否则我司将认为您同意以下所有条款。</p>
                <p>1.您授权我司保存、整理您在我司网页和手机客户端中填写的申请信息，及向我司提交的所有纸质材料和扫描影印件等。</p>
                <p>2.您选择我司为您提供的金融服务，即表示您知悉该业务的相关内容。同时授权我司在优放系统展示您的资质信息，并推荐经我司认证的贷款机构或个人协助进行贷款申请。同时，表示您不可撤销的授权我司将您的注册信息（包括但不限于手机号、登录账号）以及授权我司追踪、查询和收集的您的个人资料信息提供给为您提供服务的其他机构，并不可撤销的授权上述机构为您自动生成其平台用户名及初始密码。</p>
                <p>3.为给您更好的客户体验，您在我司平台点击进入我司认证的机构平台进行贷款，即表示您不可撤销的同意我司有权获得您在该认证机构的相关用户信息及您在该认证机构进行贷款所生成的数据信息（包括但不限于借款金额、还款期限等）。对此您知悉同意，并不可撤销的授权该认证机构将您的上述信息提供给我司。</p>
                <p>4.您不可撤销地授权我司从芝麻信用查询您的相关信息，并将您的相关信息提供给为您提供贷款服务的机构用于您本次贷款审核。同时，您同意并授权我司留存芝麻信用回传的您的相关信息，用于我司为您提供的其他服务。</p>
                <p>5.为更好地为您提供服务，您同意并授权我司可与其合作的第三方进行联合研究，并可将通过本协议获得的您的信息投入到该等联合研究中，作为研究样本。但本公司与其合作的第三方在开展上述联合研究前，应要求其合作的第三方对在联合研究中所获取的您的信息予以保密。</p>
                <p>6.我公司在此承诺，为您提供服务的过程中，不会向您收取任何费用。我司坚持免费为用户服务的原则，任何以我司或我司员工名义收取费用的，均系欺诈行为，请您予以注意。</p>
                    </div>
            </div>
        </div>
    </section>
</article>

<script type="text/javascript">
    window.panel=null;
    define('otherLoan', ['zepto', 'pop','panel','jumpApp'], function (require, exports, module) {
        var $ = require('zepto'), pop = require('pop'),call = require('jumpApp'),panel=require('panel'),md5;
        var ev = $.support.touch ? 'tap' : 'click',$wsxxSub=$('.wsxxSub');

        $wsxxSub[ev](function () {
            var str='';
            str = checking();
            console.log(str);
        });

        function checking() {
            var str='';
            return str;
        }
    });
    seajs.use(['otherLoan']);
</script>
</body>
</html>
