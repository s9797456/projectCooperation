<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <title></title>
    <style type="text/css">
        body {
            font-family: pingfang sc light;
        }
        .page{
            font-weight:bold;
        }
        .title{
            text-align: center;
            width: 100%;
            height:200px;
        }
        .qrcode{
            text-align:center;
            width:100%; 
            height:300px; 
        }
        .content{
            text-align:left;
            width: 100%;
            height:320px; 
            padding-left:200px;
        }
        .foot{
            text-align:center;
            width: 100%;
            height:100px; 
        }
	.top{
	    text-align:center;
	}
	.state{
	    line-height:35px;
	    text-indent:25px;
	}
	.header{
	    line-height:30px;
	    text-indent:25px;
	}
	.wrapper{
	    border:  1px solid black;
	}
	.right{
	    text-align:right;
	}
	.tables{
	    border-collapse:collapse;
	    border:1px solid black;
	    width:100%;
	    border-spacing:0;
	}
	.tables1{
	    border-collapse:collapse;
	    border:1px solid black;
	    width:100%;
	    border-spacing:0;
	}
	.entInfo{
	    line-height:25px;
	    text-indent:20px;
	}
	.survey{
	    text-indent:5px;
	}
    </style>
</head>
<body>
<!--第一页开始-->
<div class="page">
    <div style="width:100%;height:50px;"></div>
    <div class="title"><h1>${name!}职业信用评价报告</h1></div>
    <div class="qrcode">
        <p><img src="${qrcode!}" alt="二维码" width="150" height="150"/></p>
    </div>
    <div class="content">
        <h3>委托对象:${name!}</h3>
        <h3>出生日期:${birthday!}</h3>
        <h3>联络电话:${phone!}</h3>
        <h3>电子邮箱:${email!}</h3>
        <h3>委托时间:${gradeTime!}</h3>
        <h3>评级机构:厦门汇誉通数据科技有限公司</h3>
        <h3>有 效 期:${gradeTime!}至${valueTime!}</h3>
    </div>
</div>
<!--第一页结束-->
<!---分页标记-->
<span style="page-break-after:always;"></span>
<!--第二页开始-->
<div>
<div class="top"><h3>声&nbsp;&nbsp;&nbsp;&nbsp;明</h3></div>
<div class="state">
	<p>1、本公司在执行本信用评级业务中，遵循相关法律法规、各项规章与管理制度，遵守社会公德；恪守独立、客观、公正、诚信的原则；快速、真实、完整、连续、合法、公开的提取信用信息，科学、客观的制作信用报告，实事求是的传播信用信息与信用评级。</p>
	<p>2、本信用评级业务是本着对本公司、评级对象与相关当事方高度负责的精神，尽可能全面、详尽、深入的收集整理信用信息，依据完整详实的信用信息与资料，在调查、核实、分析的基础上，客观的描述、实事求是的工作，没有断章取义或删改有关信息与资料。</p>
	<p>3、本公司与评级对象没有现存或者预期的利益；同时对评级对象和相关当事人没有个人利益关系，对评级对象和相关当事方不存在偏见。</p>
	<p>4、本公司具备执行本信用评级业务所需的执业资质、专业评级技术以及评级经验，本次评级过程中没有运用其他评级机构或专家的工作成果。</p>
	<p>5、本次信用评级业务的原始数据来自于评级对象提交的相关材料、经评级对象确认的第三方数据，评级对象对数据的真实性、合法性无异议、并对其产生的一切后果负责。由于第三方数据所涉及的信息面很广，部分信息渠道不是本公司所能控制和核实的，同时存在疏忽和错误的可能性，本公司无法保证信息的准确性和及时性，也不承担由于错误和疏忽产生的责任。</p>
	<p>6、本报告仅供商业决策参考之用，不得作为法律诉讼的依据。未经本公司同意或授权，不得向第三方透露本报告任何内容。在任何情况下，对由于使用本报告所造成的损失，本公司不承担任何责任。</p>
	<p class="right">厦门汇誉通数据科技有限公司</p>
</div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
<div class="top"><h3>职业信用评级报告目录</h3></div>
<div>
<p><span class="page">声&nbsp;&nbsp;明</span>........................................................................................................................................................................1</p>
<p><span class="page">报告摘要</span>.................................................................................................................................................................3</p>
<p><span class="page">委托对象职业信用评价报告书</span>...........................................................................................................................4</p>
<p><span class="page">一、生活因素</span>.........................................................................................................................................................4</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;1.1生活指数.........................................................................................................................................................4</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;1.2公共信息.........................................................................................................................................................5</p>
<p><span class="page">二、教育因素</span>.........................................................................................................................................................6</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;2.1教育指数.........................................................................................................................................................6</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;2.2毕业院校、供职单位、供职行业匹配度指数..........................................................................................6</p>
<p><span class="page">三、主观因素</span>.........................................................................................................................................................7</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;3.1跳槽指数.........................................................................................................................................................7</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;3.2上进指数.........................................................................................................................................................8</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;3.3培训经历.........................................................................................................................................................8</p>
<p><span class="page">四、环境因素</span>.........................................................................................................................................................9</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;4.1层次指数.........................................................................................................................................................9</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;4.2环境指数.........................................................................................................................................................9</p>
<p><span class="page">五、能力因素</span>.........................................................................................................................................................10</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;5.1技能指数.........................................................................................................................................................10</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;5.2经验指数.........................................................................................................................................................10</p>
<p><span class="page">六、个人职业信用评级依据及方法</span>...................................................................................................................11</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;6.1评级依据.........................................................................................................................................................11</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;6.2评级方法.........................................................................................................................................................11</p>
<p><span class="page">七、委托企业信用评级综述</span>................................................................................................................................12</p>
<p><span class="page">八、说&nbsp;&nbsp;明</span>................................................................................................................................................................13</p>
</div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div class="top"><h3>报告摘要</h3></div>
	<table class="tables">
		<tr><td align="center">重要提示</td></tr>
		<tr><td><p style="text-indent: 25px;">以下内容摘自委托客户职业信用评价报告书，欲了解详细情况、合理的理解评级结论，应当详细阅读报告正文。</p></td></tr>
	</table>
	<div class="state">
		<p><span class="page">${name!}</span>委托本公司对其企业信用状况进行评级工作，授权本公司对其信用相关信息进行采集、整理、分析，并以委托企业确认的原始数据为基础形成报告。</p>
		<p>根据国家有关企业信用评级的规定，本着独立、客观、公正、诚信的原则，并按照必要的评级程序，科学、客观的制作信用报告，得出评级结果如下：</p>
		<p>一、评级对象：<span class="page">${name!}</span></p>
		<p>二、评级基准日：<span class="page">${gradeTime!}</span></p>
		<p>三、评级方法：层次分析法、财务评估法、6C分析法</p>
		<p>四、评级过程：本公司在6C要素分析法的基础上，结合评级经验，对委托企业的财务综合能力等10项信息进行采集、确认、分析，并通过科学的计算方法得出委托企业信用评价水平如下：</p>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#c1c1c1">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">一级指标</span></td>
					<td height="30" width="50%" align="center"><span class="page">得分比重(%)</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
			<#list indexRates as item>
				<tr>
					<td height="30" width="50%" align="center">${item.indexName!}</td>
					<td height="30" width="50%" align="center">${item.rateValue!}</td>
					<td height="30" width="50%" align="center">${item.description!}</td>
				</tr>
			</#list>
		</table>
		<p>五、评级结论：（满分：100 分）</p>
		<p><span class="page">${name!}</span>职业信用综合评定为<span class="page">${finalScore!}</span>分。</p>
		<p>${scoreSummary!}</p>
	</div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div class="top"><h3>委托对象职业信用评价报告书</h3></div>
	<div><p><span class="page">一、	生活因素</span></p>
	         <p><span class="page">生活因素主要是考虑其个人基本情况，研究其个人生活的稳定性对其职业的影响，亦包括其个人公共信息如诉讼、违法及公共口碑对职业的影响。</span></p></div>
	<div class="survey"><p><span class="page">1.1生活指数</span></p></div>
	<div class="entInfo">
		<p>身份证号码：${IDCard!}</p>
		<p>姓名：${name!}</p>
		<p>曾用名：${usedName!}</p>
		<p>民族：${nation!}</p>
		<p>政治面貌：${politicalOutlook!}</p>
		<p>国籍：${nationality!}</p>
		<p>籍贯：${nativePlace!}</p>
		<p>婚姻状况：${maritalStatus!}</p>
		<p>生育情况：${fertilityCondition!}</p>
		<p>户籍地址：${permanentAddress!}</p>
		<p>现居住地址：${presentAddress!}</p>
		<p>身份证签发机关：${IDIssuingAgency!}</p>
		<p>身份证有效期：${IDTermStart!}-${IDTermEnd!}</p>
		<p>现居住地址邮政编码：${presentZipCode!}</p>
	</div>
	<div><p>注：以上数据来自委托对象自报</p></div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div class="survey"><p><span class="page">1.2公共信息</span></p></div>
	<div class="survey"><p><span class="page">1.2.1舆情信息</span></p></div>
	<div class="entInfo">截至2017年4月10日，根据厦门汇誉通大数据信息处理平台显示，委托对象舆情信息共有0条，其中正面信息0条、负面信息0条、中性信息0条。相关信息如下：</div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">新闻标题</span></td>
					<td height="30" width="50%" align="center"><span class="page">新闻摘要</span></td>
					<td height="30" width="50%" align="center"><span class="page">来源/日期</span></td>
					<td height="30" width="50%" align="center"><span class="page">信息类型</span></td>
				</tr>
		</table>
	<div><p>注：以上数据来自厦门汇誉通大数据信息处理平台</p></div>
</div>
<div>
	<div class="survey"><p><span class="page">1.2.2诉讼信息</span></p></div>
	<div class="entInfo">截至2017年4月10日，通过查询中国裁判文书网以及厦门汇誉通大数据信息处理平台显示，委托方涉及已决诉讼信息0条、未决诉讼信息0条。</div>
	<div style="text-indent:10px;"><p>●已决诉讼信息</p></div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
			<tr>
				<td height="30" width="50%" align="center">判决文书</td>
				<td height="30" width="50%" align="center">案件类型</td>
				<td height="30" width="50%" align="center">案号</td>
			</tr>
	</table>
	<div style="text-indent:10px;"><p>●未决诉讼信息</p></div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
			<tr>
				<td height="30" width="50%" align="center">判决文书</td>
				<td height="30" width="50%" align="center">案件类型</td>
				<td height="30" width="50%" align="center">案号</td>
			</tr>
	</table>
	<div><p>注：以上数据来自厦门汇誉通大数据信息处理平台</p></div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div><p><span class="page">二、	教育因素</span></p>
			<p><span class="page">教育因素包括其学历、证书的真伪等。</span></p></div>
	<div class="survey"><p><span class="page">2.1教育指数</span></p></div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">教育指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<tr>
					<td height="30" width="50%" align="center">学历</td>
					<td height="30" width="50%" align="center">${educationScore!}</td>
					<td height="30" width="50%" align="center">${educationRemarks!}</td>
				</tr>
				<tr>
					<td height="30" width="50%" align="center">毕业院校</td>
					<td height="30" width="50%" align="center">${universityScore!}</td>
					<td height="30" width="50%" align="center">${universityRemarks!}</td>
				</tr>
				<tr>
					<td height="30" width="50%" align="center">专业</td>
					<td height="30" width="50%" align="center">${majorScore!}</td>
					<td height="30" width="50%" align="center">${majorRemarks!}</td>
				</tr>
				<tr>
					<td height="30" width="50%" align="center">证书编号</td>
					<td height="30" width="50%" align="center">${diplomaNoScore!}</td>
					<td height="30" width="50%" align="center">${diplomaNoRemarks!}</td>
				</tr>
		</table>
	<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
</div>
<div>
	<div class="survey"><p><span class="page">2.2毕业院校、供职单位、供职行业匹配度指数</span></p></div>
	<div style="float:left">
        <img src="${image1!}" alt="采用三个圆圈的交集图" width="600" height="400"/>
    </div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div><p><span class="page">三、	主观因素</span></p>
			<p><span class="page">主观因素是指个人的履职意愿或频繁地跳槽，包括其职业的忠诚度及其职业履历是否注水等。</span></p></div>
	<div class="survey"><p><span class="page">3.1跳槽指数</span></p></div>
	<p><span class="page">3.1.1近两次工作平均年限</span></p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;${averageLife!}</p>
	<p><span class="page">3.1.2最长工作年限</span></p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;${longestLife!}</p>
	<p><span class="page">3.1.3职业履历</span></p>
	<p>3.1.3.1就职单位性质变化</p>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">就职单位</span></td>
					<td height="30" width="50%" align="center"><span class="page">单位性质</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<#list careerChange1 as item>
				<tr>
					<td height="30" width="50%" align="center">${item.name!}</td>
					<td height="30" width="50%" align="center">${item.value!}</td>
					<td height="30" width="50%" align="center">${item.remark!}</td>
				</tr>
				</#list>
	</table>
	<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
	<p>3.1.3.2就职单位规模变化</p>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">就职单位</span></td>
					<td height="30" width="50%" align="center"><span class="page">单位规模</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<#list careerChange2 as item>
				<tr>
					<td height="30" width="50%" align="center">${item.name!}</td>
					<td height="30" width="50%" align="center">${item.value!}</td>
					<td height="30" width="50%" align="center">${item.remark!}</td>
				</tr>
				</#list>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
		<p>3.1.3.3行业变动</p>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">就职单位</span></td>
					<td height="30" width="50%" align="center"><span class="page">所处行业/从事工作性质</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<#list careerChange3 as item>
				<tr>
					<td height="30" width="50%" align="center">${item.name!}</td>
					<td height="30" width="50%" align="center">${item.value!}</td>
					<td height="30" width="50%" align="center">${item.remark!}</td>
				</tr>
				</#list>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
		<p>3.1.3.4工作年限风险提示</p>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page"></span></td>
					<td height="30" width="50%" align="center"><span class="page">委托对象</span></td>
					<td height="30" width="50%" align="center"><span class="page">同行业</span></td>
					<td height="30" width="50%" align="center"><span class="page">同岗位</span></td>
				</tr>
			<tr>
			    <td height="30" width="50%" align="center">总工作年限</td>
				<td height="30" width="50%" align="center">${allLife1!}</td>
				<td height="30" width="50%" align="center">${allLife2!}</td>
				<td height="30" width="50%" align="center">${allLife3!}</td>
			</tr>
			<tr>
			    <td height="30" width="50%" align="center">单位平均工作年限</td>
				<td height="30" width="50%" align="center">${averageLife1!}</td>
				<td height="30" width="50%" align="center">${averageLife2!}</td>
				<td height="30" width="50%" align="center">${averageLife3!}</td>
			</tr>
			<tr>
			    <td height="30" width="50%" align="center">同行业工作年限</td>
				<td height="30" width="50%" align="center">${sameLife1!}</td>
				<td height="30" width="50%" align="center">${sameLife2!}</td>
				<td height="30" width="50%" align="center">${sameLife3!}</td>
			</tr>
		</table>
	<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
	<div style="float:left">
        <img src="${image2!}" alt="工作年限风险" width="600" height="400"/>
    </div>
</div>
<div class="survey"><p><span class="page">3.2上进指数</span></p></div>
<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">跳槽指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
			<tr>
			    <td height="30" width="50%" align="center">是否正在或有自身素质提高的意愿</td>
				<td height="30" width="50%" align="center">${trainScore!}</td>
				<td height="30" width="50%" align="center">${trainRemarks!}</td>
			</tr>
			<tr>
			    <td height="30" width="50%" align="center">再教育或培训证书编号</td>
				<td height="30" width="50%" align="center">${trainNoScore!}</td>
				<td height="30" width="50%" align="center">${trainNoRemarks!}</td>
			</tr>
		</table>
	<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
<div class="survey"><p><span class="page">3.3培训经历</span></p></div>
<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">开始时间</span></td>
					<td height="30" width="50%" align="center"><span class="page">结束时间</span></td>
					<td height="30" width="50%" align="center"><span class="page">培训机构</span></td>
					<td height="30" width="50%" align="center"><span class="page">培训内容</span></td>
				</tr>
				<#list trains as item>
				<tr>
					<td height="30" width="50%" align="center">${item.startTime!}</td>
					<td height="30" width="50%" align="center">${item.endTime!}</td>
					<td height="30" width="50%" align="center">${item.trainOrg!}</td>
					<td height="30" width="50%" align="center">${item.trainContent!}</td>
				</tr>
				</#list>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div><p><span class="page">四、	环境因素</span></p>
			<p><span class="page">环境因素是指面对新环境、新挑战时，有足够的弹性和动力去接受改变，并享受变化，迎向挑战！</span></p></div>
	       <div class="survey"><p><span class="page">4.1层次指数</span></p></div>
	       <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">层次指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
			<tr>
				<td height="30" width="50%" align="center">职务</td>
				<td height="30" width="50%" align="center">${postScore!}</td>
				<td height="30" width="50%" align="center">${postRemarks!}</td>
			</tr>
			<tr>
				<td height="30" width="50%" align="center">职业资质</td>
				<td height="30" width="50%" align="center">${qualificationsScore!}</td>
				<td height="30" width="50%" align="center">${qualificationsRemarks!}</td>
			</tr>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
		<div class="survey"><p><span class="page">4.2环境指数</span></p></div>
	       <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">环境指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<tr>
				<td height="30" width="50%" align="center">单位性质</td>
				<td height="30" width="50%" align="center">${unitPropertiesScore!}</td>
				<td height="30" width="50%" align="center">${unitPropertiesRemarks!}</td>
			</tr>
			<tr>
				<td height="30" width="50%" align="center">单位规模</td>
				<td height="30" width="50%" align="center">${scaleScore!}</td>
				<td height="30" width="50%" align="center">${scaleRemarks!}</td>
			</tr>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div><p><span class="page">五、	能力因素</span></p>
			<p><span class="page">专业技能及行业领域积累的经验，个人技能的不断提升，在岗位上可以游刃有余，如鱼得水以及工作中的投入，对其职业能力都有重要的影响。</span></p></div>
	<div class="survey"><p><span class="page">5.1技能指数</span></p></div>
	       <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">技能指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
			<tr>
				<td height="30" width="50%" align="center">专业知识技能</td>
				<td height="30" width="50%" align="center">${skillsScore!}</td>
				<td height="30" width="50%" align="center">${skillsRemarks!}</td>
			</tr>
			<tr>
				<td height="30" width="50%" align="center">专业技能培训</td>
				<td height="30" width="50%" align="center">${skillsTrainScore!}</td>
				<td height="30" width="50%" align="center">${skillsTrainRemarks!}</td>
			</tr>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
		<div class="survey"><p><span class="page">5.2经验指数</span></p></div>
	       <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
				<tr>
					<td height="30" width="50%" align="center"><span class="page">经验指数</span></td>
					<td height="30" width="50%" align="center"><span class="page">评分</span></td>
					<td height="30" width="50%" align="center"><span class="page">备注</span></td>
				</tr>
				<tr>
				<td height="30" width="50%" align="center">总工作年限</td>
				<td height="30" width="50%" align="center">${allLifeScore!}</td>
				<td height="30" width="50%" align="center">${allLifeRemarks!}</td>
			</tr>
			<tr>
				<td height="30" width="50%" align="center">同行业工作年限(行业经验)</td>
				<td height="30" width="50%" align="center">${sameLifeScore!}</td>
				<td height="30" width="50%" align="center">${sameLifeRemarks!}</td>
			</tr>
		</table>
		<div><p>注：以上数据来自委托对象自报以及厦门汇誉通大数据信息处理平台</p></div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div><p><span class="page">六、	个人职业信用评级依据及方法</span></p></div>
	<div class="survey"><p><span class="page">6.1评级依据</span></p></div>
	<div style="text-indent:10px;"><p><span class="page">●国家标准和行业标准</span></p></div>
	<div class="entInfo"><p>1)	GB/T 28041-2011 基于电子商务活动的交易主体个人信用评价指标体系及表示规范</p>
                             <p>2)  GB/T 28042-2011 中文名：基于电子商务活动的交易主体个人信用档案规范</p>
                             <p>3)	GB/T 26819-2011 信用主体标识规范</p>
                             <p>4)	GB/T 26818-2011 个人信用调查报告格式规范基本信息报告</p>
                             <p>5)	GB/T 23792-2009 信用标准化工作指南</p>
                             <p>6)	GB/T 22117-2008 信用基本术语</p>
                             <p>7)	GB/T 22119-2008 信用中介组织评价服务规范信用评级机构</p>
	</div>
	<div style="text-indent:10px;"><p><span class="page">●数据来源</span></p></div>
	<div class="entInfo"><p>1)	委托对象提交信息和所属证明文件</p>
                             <p>2)	厦门汇誉通大数据信息处理平台</p>
                             <p>3) 互联网公开信息查询</p>
	</div>
	<div class="survey"><p><span class="page">6.2评级方法</span></p></div>
	<div style="text-indent:10px;">
		<p>●定性研究与定量分析相结合</p>
		<p>●动态性与静态性相结合</p>
		<p>●专家评估与模版打分相结合</p>
		<p>●线性模型分析法</p>
		<p>●信用监控mixing（credit monitor model）:KMV模型</p>
		<p>●风险矩阵：Risk metrics模型</p>
		<p>●信用矩阵：Credit metrics模型</p>
	</div>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div class="page">
	<div><p>七、	委托对象信用评价综述</p></div>
</div>
<div>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="black">
			<tr>
				<td height="30" width="20%" align="center">分数</td>
				<td height="30" width="80%" align="center">分数解释</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">95-100</td>
				<td height="30" width="80%">个人职业信用极好，信用能力很强，工作稳定性极强，几乎没有不守信的情况，个人能力极强，社会责任心极强。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">85-95</td>
				<td height="30" width="80%">个人职业信用优良，信用能力可靠，工作稳定性很强，该人可以信任，有较强的责任心。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">75-85</td>
				<td height="30" width="80%">个人职业信用较好，信用能力较稳定，工作稳定性较强，个人品格和能力超过个人信用平均水平，信用相关方评价较高。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">70-75</td>
				<td height="30" width="80%">个人职业信用一般，信用能力基本具备，工作稳定性容易产生一定的波动，个人的品格和能力居于平均水平，有时可能会出现不守信的情况，应密切关注该人的工作情况。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">65-70</td>
				<td height="30" width="80%">个人职业信用欠佳，信用能力不稳定，工作稳定性容易产生较大的波动，个人的品格和能力受到一定的质疑。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">60-65</td>
				<td height="30" width="80%">个人职业信用较差，信用能力较低，个人品格和能力受到普遍质疑。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">50-60</td>
				<td height="30" width="80%">个人职业信用很差，信用能力很低，个人品格和能力较差，出现不守信用的可能性较高。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">40-50</td>
				<td height="30" width="80%">个人职业信用极差，个人品格和能力很差。</td>
			</tr>
			<tr>
				<td height="30" width="20%" align="center">0-40</td>
				<td height="30" width="80%">没有信用，个人品格和能力极差。</td>
			</tr>
	</table>
</div>
<!---分页标记-->
<span style="page-break-after:always;"></span>
<div>
	<div class="page">
	<div><p>八、	说明</p></div>
   </div>
	<div class="state">
		<p>尽管本公司尽力确保所提供信息的准确性和完整性，但由于所涉及的信息面很广，部分信息渠道以及直接从委托对象获得的信息不是本公司所能控制的和核实的，同时存在疏忽和错误的可能性，本公司无法保证信息的准确性和及时性，也不承担由于错误和遗漏产生的责任。</p>
		<p>(一) 本评级报告仅限于报告中载明的评级目的使用。</p>
		<p>(二) 本评级报告仅供委托对象为本次评估目的送交有关当事方备案使用，未征得本公司同意，评级报告的全部或者部分内容不得被摘抄、引用或者披露于公开媒体，法律、法规规定以及相关当事方有约定的除外。</p>
		<p>(三) 本评级报告所揭示的评级结论应作为一个整体使用；有效期为报告出具日期起三个月以内。</p>
		<p>(四) 有效期内，若相关信息以及评价标准发生变化，对评级造成影响时,不能直接使用本评级结论，需对评级进行调整或重新评估。</p>
	</div>
</div>
</body>
</html>