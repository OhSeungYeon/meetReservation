package com.gbm.edu.api.resttemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbm.edu.api.model.ResBzmBaseModel;

@Service
public class RestTemplateService {

	@Value("${shopping.api.scheme}")
	private String API_SCHEME;

	@Value("${shopping.api.center.host}")
	private String API_CENTER_HOST;

	@Value("${shopping.api.center.port}")
	private String API_PORT;

	@Value("${kakao.api.scheme}")
	private String KAKAO_SCHEME;

	@Value("${kakao.api.center.host}")
	private String KAKAO_CENTER_HOST;

	@Value("${kakao.rest-api-key}")
	private String KAKAO_REST_API_KEY;

	@Value("${shopping.api.use}")
	private boolean API_USE;

	@Autowired
	private RestTemplate restTemplate;


	/**
	 * 톡주문 API Post Method 호출(+Json Param)
	 *
	 * @since 2020. 04. 07
	 * @version 0.1
	 * @author th kim
	 * @param paramMap 파라미터(Body)
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ResBzmBaseModel callBzmApiPostWithJsonParam() throws JsonParseException, JsonMappingException, IOException {

		HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> sessionInfo = new HashMap<>();
        sessionInfo.put("sessionChanlCd","TLK");
        sessionInfo.put("sessionUserId","TALK");
        sessionInfo.put("sessionUserIp","");

        HashMap<String, Object> inArsCustCnfReqInfo = new HashMap<>();
        inArsCustCnfReqInfo.put("tmpOrdCd","");
        inArsCustCnfReqInfo.put("idenNoGbnCd","0");
        inArsCustCnfReqInfo.put("idenNo","01051924777");

        paramMap.put("sessionInfo", sessionInfo);
        paramMap.put("inArsCustCnfReqInfo", inArsCustCnfReqInfo);

		URI apiUri = UriComponentsBuilder.newInstance()
				.scheme(API_SCHEME)
                .host(API_CENTER_HOST)
                .port(API_PORT)
                .path("esbTlkCustInfoQry/getTlkCustInfo/10/rspService.gs")
                .buildAndExpand("")
                .toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));

		JSONObject resultObj = new JSONObject();
		resultObj.putAll(paramMap);
		String paramJsonString = resultObj.toJSONString();
		// 배열 따옴표 제거
		paramJsonString = paramJsonString.replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\"");
		paramJsonString = paramJsonString.replace("\\\\\"", "\"").replace("\\{", "{").replace("}\"", "}");

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(paramJsonString, headers);
System.out.println("apiUri : " + apiUri.toString() );
System.out.println("httpEntity : " + httpEntity );
		String resJsonString = restTemplate.exchange(apiUri.toString(), HttpMethod.POST, httpEntity, String.class).getBody();

		// Bzm API는 Response Content type text/plain; 이라서 변환을 시켜준다.
		ObjectMapper mapper = new ObjectMapper();
		ResBzmBaseModel resultObject = mapper.readValue(resJsonString, new TypeReference<ResBzmBaseModel>(){});

		return resultObject;
	}

	/**
	 * GS Shop API Post Method 호출
	 *
	 * @since 2020. 04. 08
	 * @version 0.1
	 * @author thkim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T callShoppingApiPost( T returnType, MultiValueMap<String, Object> paramMap) {

		HttpHeaders headers = new HttpHeaders();

		URI apiUri = UriComponentsBuilder.newInstance()
					.scheme(API_SCHEME)
	                .host(API_CENTER_HOST)
	                .port(API_PORT)
	                .path("esbTlkCustInfoQry/getTlkCustInfo/10/rspService.gs")
	                .buildAndExpand("")
	                .toUri();

		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
		return (T) restTemplate.exchange(apiUri.toString(), HttpMethod.POST, httpEntity, returnType.getClass(), paramMap).getBody();
	}

	/**
	 * GS Shop API Post Method 호출
	 *
	 * @since 2020. 04. 08
	 * @version 0.1
	 * @author thkim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T callShoppingApiIntf( T returnType, HashMap<String, Object> paramMap) {

		HttpHeaders headers = new HttpHeaders();

		URI apiUri = UriComponentsBuilder.newInstance()
					.scheme(API_SCHEME)
	                .host(API_CENTER_HOST)
	                .port(API_PORT)
	                .path("esbTlkCustInfoQry/getTlkCustInfo.gs")
	                .buildAndExpand("")
	                .toUri();
System.out.println(" paramMap : " + paramMap);
System.out.println(" apiUri : " + apiUri);
		HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
		return (T) restTemplate.exchange(apiUri.toString(), HttpMethod.POST, httpEntity, returnType.getClass(), paramMap).getBody();
	}

	/**
	 * GS Shop API Post Method 호출
	 *
	 * @since 2020. 04. 08
	 * @version 0.1
	 * @author thkim
	 * @return
	 */
	public String callIFESBTLK6030( HashMap<String, Object> paramMap) {

		HttpHeaders headers = new HttpHeaders();

		URI apiUri = UriComponentsBuilder.newInstance()
					.scheme(API_SCHEME)
	                .host(API_CENTER_HOST)
	                .port(API_PORT)
	                .path("esbTlkCustInfoQry/getTlkCustCnf.gs")
	                .buildAndExpand("")
	                .toUri();
System.out.println(" paramMap : " + paramMap);
System.out.println(" apiUri : " + apiUri);
		HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
		return restTemplate.exchange(apiUri.toString(), HttpMethod.POST, httpEntity, String.class, paramMap).getBody();
	}

	/**
	 * GS Shop API Post Method 호출
	 *
	 * @since 2020. 04. 08
	 * @version 0.1
	 * @author thkim
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String callShopApiPost(String procId, HashMap<String, Object> paramMap, String ifesId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = null;
		String restRtn = null;
		/*인터페이스구분에 따라 호출 URL 변경*/
		switch(ifesId) {
			case "IFESBTLK6044" : //즉시할인조회
				path = "esbTlkPayMean/getTlkCardImmDcInfo";
				break;
			case "IFESBTLK6010" : //방송상품조회
				path = "esbTlkBroadPrdQry/getTlkCurrBroadPrd";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsPrdReqAnswrInfo\":{\"values\":{\"preBroadInsuPrdYn\":null,\"currBroadInsuPrdYn\":\"N\",\"preBroadPrdCnt\":0,\"grpInfo\":\"N\",\"rsltCd\":\"00\",\"tmpOrdCd\":531182760,\"preBroadGrpInfo\":\"N\",\"currBroadPrdChrDcInfo\":\"\",\"currBroadPrdCnt\":2}},\"outLastPrdInfoList\":{\"values\":[]},\"outOnairPrdInfoList\":{\"values\":[{\"prdDtlUrl\":\"http://m.gsshop.com/prd/prdDescForKakao.gs?prdid=35220021\",\"prdExposUprc\":59800,\"broadDt\":\"20200417164000\",\"broadId\":\"195139\",\"prdImgUrl\":\"http://image.gsshop.com/image/35/22/35220021_O1.jpg\",\"prdCd\":35220021,\"setPrdYn\":\"N\",\"prdNm\":\"폴햄 경량 구스다운 조끼\",\"exposPrdNm\":\"[폴햄] 19년 신상 구스다운 경량베스트 2종 세트(테스트)\"},{\"prdDtlUrl\":\"http://m.gsshop.com/prd/prdDescForKakao.gs?prdid=37177233\",\"prdExposUprc\":169000,\"broadDt\":\"20200417164000\",\"broadId\":\"195139\",\"prdImgUrl\":\"http://image.gsshop.com/image/37/17/37177233_O1.jpg\",\"prdCd\":37177233,\"setPrdYn\":\"N\",\"prdNm\":\"원더브라 쎄트\",\"exposPrdNm\":\"[팬티사이즈 선택] 원더브라 최신상 풀커버리지 스킨온스킨(테스트)\"}]}},\"headerSet\":{\"timestamp1\":\"1587097573228\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"9459c47d-4984-4b67-bbb3-2e09900063c3\",\"timestamp7\":\"2020-04-17 13:26:13.235\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6010\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkBroadPrdQry/getTlkCurrBroadPrd/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 방송상품조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6020" : //상품속성조회
				path = "esbTlkPrdQry/getTlkPrdAttrList";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsPrdAttList\":{\"values\":[{\"attrPrdRepCd\":\"35220021001\",\"attrWhlVal\":\"블랙,90,블랙,90\",\"arsBroadSeq\":1,\"ordPsblQty\":9974,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,블랙,90\",\"attrPrdCd\":35220021001},{\"attrPrdRepCd\":\"35220021002\",\"attrWhlVal\":\"블랙,90,블랙,95\",\"arsBroadSeq\":2,\"ordPsblQty\":9996,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,블랙,95\",\"attrPrdCd\":35220021002},{\"attrPrdRepCd\":\"35220021003\",\"attrWhlVal\":\"블랙,90,블랙,100\",\"arsBroadSeq\":3,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,블랙,100\",\"attrPrdCd\":35220021003},{\"attrPrdRepCd\":\"35220021004\",\"attrWhlVal\":\"블랙,90,블랙,105\",\"arsBroadSeq\":4,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,블랙,105\",\"attrPrdCd\":35220021004},{\"attrPrdRepCd\":\"35220021005\",\"attrWhlVal\":\"블랙,90,베이지,90\",\"arsBroadSeq\":5,\"ordPsblQty\":9995,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,베이지,90\",\"attrPrdCd\":35220021005},{\"attrPrdRepCd\":\"35220021006\",\"attrWhlVal\":\"블랙,90,베이지,95\",\"arsBroadSeq\":6,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,베이지,95\",\"attrPrdCd\":35220021006},{\"attrPrdRepCd\":\"35220021007\",\"attrWhlVal\":\"블랙,90,베이지,100\",\"arsBroadSeq\":7,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,베이지,100\",\"attrPrdCd\":35220021007},{\"attrPrdRepCd\":\"35220021008\",\"attrWhlVal\":\"블랙,90,베이지,105\",\"arsBroadSeq\":8,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,베이지,105\",\"attrPrdCd\":35220021008},{\"attrPrdRepCd\":\"35220021009\",\"attrWhlVal\":\"블랙,90,멜란지그레이,90\",\"arsBroadSeq\":9,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,멜란지그레이,90\",\"attrPrdCd\":35220021009},{\"attrPrdRepCd\":\"35220021010\",\"attrWhlVal\":\"블랙,90,멜란지그레이,95\",\"arsBroadSeq\":10,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,멜란지그레이,95\",\"attrPrdCd\":35220021010},{\"attrPrdRepCd\":\"35220021011\",\"attrWhlVal\":\"블랙,90,멜란지그레이,100\",\"arsBroadSeq\":11,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,멜란지그레이,100\",\"attrPrdCd\":35220021011},{\"attrPrdRepCd\":\"35220021012\",\"attrWhlVal\":\"블랙,90,멜란지그레이,105\",\"arsBroadSeq\":12,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,90,멜란지그레이,105\",\"attrPrdCd\":35220021012},{\"attrPrdRepCd\":\"35220021013\",\"attrWhlVal\":\"블랙,95,블랙,90\",\"arsBroadSeq\":13,\"ordPsblQty\":9996,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,블랙,90\",\"attrPrdCd\":35220021013},{\"attrPrdRepCd\":\"35220021014\",\"attrWhlVal\":\"블랙,95,블랙,95\",\"arsBroadSeq\":14,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,블랙,95\",\"attrPrdCd\":35220021014},{\"attrPrdRepCd\":\"35220021015\",\"attrWhlVal\":\"블랙,95,블랙,100\",\"arsBroadSeq\":15,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,블랙,100\",\"attrPrdCd\":35220021015},{\"attrPrdRepCd\":\"35220021016\",\"attrWhlVal\":\"블랙,95,블랙,105\",\"arsBroadSeq\":16,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,블랙,105\",\"attrPrdCd\":35220021016},{\"attrPrdRepCd\":\"35220021017\",\"attrWhlVal\":\"블랙,95,베이지,90\",\"arsBroadSeq\":17,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,베이지,90\",\"attrPrdCd\":35220021017},{\"attrPrdRepCd\":\"35220021018\",\"attrWhlVal\":\"블랙,95,베이지,95\",\"arsBroadSeq\":18,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,베이지,95\",\"attrPrdCd\":35220021018},{\"attrPrdRepCd\":\"35220021019\",\"attrWhlVal\":\"블랙,95,베이지,100\",\"arsBroadSeq\":19,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,베이지,100\",\"attrPrdCd\":35220021019},{\"attrPrdRepCd\":\"35220021020\",\"attrWhlVal\":\"블랙,95,베이지,105\",\"arsBroadSeq\":20,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,베이지,105\",\"attrPrdCd\":35220021020},{\"attrPrdRepCd\":\"35220021021\",\"attrWhlVal\":\"블랙,95,멜란지그레이,90\",\"arsBroadSeq\":21,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,멜란지그레이,90\",\"attrPrdCd\":35220021021},{\"attrPrdRepCd\":\"35220021022\",\"attrWhlVal\":\"블랙,95,멜란지그레이,95\",\"arsBroadSeq\":22,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,멜란지그레이,95\",\"attrPrdCd\":35220021022},{\"attrPrdRepCd\":\"35220021023\",\"attrWhlVal\":\"블랙,95,멜란지그레이,100\",\"arsBroadSeq\":23,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,멜란지그레이,100\",\"attrPrdCd\":35220021023},{\"attrPrdRepCd\":\"35220021024\",\"attrWhlVal\":\"블랙,95,멜란지그레이,105\",\"arsBroadSeq\":24,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,95,멜란지그레이,105\",\"attrPrdCd\":35220021024},{\"attrPrdRepCd\":\"35220021025\",\"attrWhlVal\":\"블랙,100,블랙,90\",\"arsBroadSeq\":25,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,블랙,90\",\"attrPrdCd\":35220021025},{\"attrPrdRepCd\":\"35220021026\",\"attrWhlVal\":\"블랙,100,블랙,95\",\"arsBroadSeq\":26,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,블랙,95\",\"attrPrdCd\":35220021026},{\"attrPrdRepCd\":\"35220021027\",\"attrWhlVal\":\"블랙,100,블랙,100\",\"arsBroadSeq\":27,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,블랙,100\",\"attrPrdCd\":35220021027},{\"attrPrdRepCd\":\"35220021028\",\"attrWhlVal\":\"블랙,100,블랙,105\",\"arsBroadSeq\":28,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,블랙,105\",\"attrPrdCd\":35220021028},{\"attrPrdRepCd\":\"35220021029\",\"attrWhlVal\":\"블랙,100,베이지,90\",\"arsBroadSeq\":29,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,베이지,90\",\"attrPrdCd\":35220021029},{\"attrPrdRepCd\":\"35220021030\",\"attrWhlVal\":\"블랙,100,베이지,95\",\"arsBroadSeq\":30,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,베이지,95\",\"attrPrdCd\":35220021030},{\"attrPrdRepCd\":\"35220021031\",\"attrWhlVal\":\"블랙,100,베이지,100\",\"arsBroadSeq\":31,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,베이지,100\",\"attrPrdCd\":35220021031},{\"attrPrdRepCd\":\"35220021032\",\"attrWhlVal\":\"블랙,100,베이지,105\",\"arsBroadSeq\":32,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,베이지,105\",\"attrPrdCd\":35220021032},{\"attrPrdRepCd\":\"35220021033\",\"attrWhlVal\":\"블랙,100,멜란지그레이,90\",\"arsBroadSeq\":33,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,멜란지그레이,90\",\"attrPrdCd\":35220021033},{\"attrPrdRepCd\":\"35220021034\",\"attrWhlVal\":\"블랙,100,멜란지그레이,95\",\"arsBroadSeq\":34,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,멜란지그레이,95\",\"attrPrdCd\":35220021034},{\"attrPrdRepCd\":\"35220021035\",\"attrWhlVal\":\"블랙,100,멜란지그레이,100\",\"arsBroadSeq\":35,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,멜란지그레이,100\",\"attrPrdCd\":35220021035},{\"attrPrdRepCd\":\"35220021036\",\"attrWhlVal\":\"블랙,100,멜란지그레이,105\",\"arsBroadSeq\":36,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,100,멜란지그레이,105\",\"attrPrdCd\":35220021036},{\"attrPrdRepCd\":\"35220021037\",\"attrWhlVal\":\"블랙,105,블랙,90\",\"arsBroadSeq\":37,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,블랙,90\",\"attrPrdCd\":35220021037},{\"attrPrdRepCd\":\"35220021038\",\"attrWhlVal\":\"블랙,105,블랙,95\",\"arsBroadSeq\":38,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,블랙,95\",\"attrPrdCd\":35220021038},{\"attrPrdRepCd\":\"35220021039\",\"attrWhlVal\":\"블랙,105,블랙,100\",\"arsBroadSeq\":39,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,블랙,100\",\"attrPrdCd\":35220021039},{\"attrPrdRepCd\":\"35220021040\",\"attrWhlVal\":\"블랙,105,블랙,105\",\"arsBroadSeq\":40,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,블랙,105\",\"attrPrdCd\":35220021040},{\"attrPrdRepCd\":\"35220021041\",\"attrWhlVal\":\"블랙,105,베이지,90\",\"arsBroadSeq\":41,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,베이지,90\",\"attrPrdCd\":35220021041},{\"attrPrdRepCd\":\"35220021042\",\"attrWhlVal\":\"블랙,105,베이지,95\",\"arsBroadSeq\":42,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,베이지,95\",\"attrPrdCd\":35220021042},{\"attrPrdRepCd\":\"35220021043\",\"attrWhlVal\":\"블랙,105,베이지,100\",\"arsBroadSeq\":43,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,베이지,100\",\"attrPrdCd\":35220021043},{\"attrPrdRepCd\":\"35220021044\",\"attrWhlVal\":\"블랙,105,베이지,105\",\"arsBroadSeq\":44,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,베이지,105\",\"attrPrdCd\":35220021044},{\"attrPrdRepCd\":\"35220021045\",\"attrWhlVal\":\"블랙,105,멜란지그레이,90\",\"arsBroadSeq\":45,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,멜란지그레이,90\",\"attrPrdCd\":35220021045},{\"attrPrdRepCd\":\"35220021046\",\"attrWhlVal\":\"블랙,105,멜란지그레이,95\",\"arsBroadSeq\":46,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,멜란지그레이,95\",\"attrPrdCd\":35220021046},{\"attrPrdRepCd\":\"35220021047\",\"attrWhlVal\":\"블랙,105,멜란지그레이,100\",\"arsBroadSeq\":47,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,멜란지그레이,100\",\"attrPrdCd\":35220021047},{\"attrPrdRepCd\":\"35220021048\",\"attrWhlVal\":\"블랙,105,멜란지그레이,105\",\"arsBroadSeq\":48,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"블랙,105,멜란지그레이,105\",\"attrPrdCd\":35220021048},{\"attrPrdRepCd\":\"35220021049\",\"attrWhlVal\":\"차콜,90,블랙,90\",\"arsBroadSeq\":49,\"ordPsblQty\":9995,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,블랙,90\",\"attrPrdCd\":35220021049},{\"attrPrdRepCd\":\"35220021050\",\"attrWhlVal\":\"차콜,90,블랙,95\",\"arsBroadSeq\":50,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,블랙,95\",\"attrPrdCd\":35220021050},{\"attrPrdRepCd\":\"35220021051\",\"attrWhlVal\":\"차콜,90,블랙,100\",\"arsBroadSeq\":51,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,블랙,100\",\"attrPrdCd\":35220021051},{\"attrPrdRepCd\":\"35220021052\",\"attrWhlVal\":\"차콜,90,블랙,105\",\"arsBroadSeq\":52,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,블랙,105\",\"attrPrdCd\":35220021052},{\"attrPrdRepCd\":\"35220021053\",\"attrWhlVal\":\"차콜,90,베이지,90\",\"arsBroadSeq\":53,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,베이지,90\",\"attrPrdCd\":35220021053},{\"attrPrdRepCd\":\"35220021054\",\"attrWhlVal\":\"차콜,90,베이지,95\",\"arsBroadSeq\":54,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,베이지,95\",\"attrPrdCd\":35220021054},{\"attrPrdRepCd\":\"35220021055\",\"attrWhlVal\":\"차콜,90,베이지,100\",\"arsBroadSeq\":55,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,베이지,100\",\"attrPrdCd\":35220021055},{\"attrPrdRepCd\":\"35220021056\",\"attrWhlVal\":\"차콜,90,베이지,105\",\"arsBroadSeq\":56,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,베이지,105\",\"attrPrdCd\":35220021056},{\"attrPrdRepCd\":\"35220021057\",\"attrWhlVal\":\"차콜,90,멜란지그레이,90\",\"arsBroadSeq\":57,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,멜란지그레이,90\",\"attrPrdCd\":35220021057},{\"attrPrdRepCd\":\"35220021058\",\"attrWhlVal\":\"차콜,90,멜란지그레이,95\",\"arsBroadSeq\":58,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,멜란지그레이,95\",\"attrPrdCd\":35220021058},{\"attrPrdRepCd\":\"35220021059\",\"attrWhlVal\":\"차콜,90,멜란지그레이,100\",\"arsBroadSeq\":59,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,멜란지그레이,100\",\"attrPrdCd\":35220021059},{\"attrPrdRepCd\":\"35220021060\",\"attrWhlVal\":\"차콜,90,멜란지그레이,105\",\"arsBroadSeq\":60,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,90,멜란지그레이,105\",\"attrPrdCd\":35220021060},{\"attrPrdRepCd\":\"35220021061\",\"attrWhlVal\":\"차콜,95,블랙,90\",\"arsBroadSeq\":61,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,블랙,90\",\"attrPrdCd\":35220021061},{\"attrPrdRepCd\":\"35220021062\",\"attrWhlVal\":\"차콜,95,블랙,95\",\"arsBroadSeq\":62,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,블랙,95\",\"attrPrdCd\":35220021062},{\"attrPrdRepCd\":\"35220021063\",\"attrWhlVal\":\"차콜,95,블랙,100\",\"arsBroadSeq\":63,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,블랙,100\",\"attrPrdCd\":35220021063},{\"attrPrdRepCd\":\"35220021064\",\"attrWhlVal\":\"차콜,95,블랙,105\",\"arsBroadSeq\":64,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,블랙,105\",\"attrPrdCd\":35220021064},{\"attrPrdRepCd\":\"35220021065\",\"attrWhlVal\":\"차콜,95,베이지,90\",\"arsBroadSeq\":65,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,베이지,90\",\"attrPrdCd\":35220021065},{\"attrPrdRepCd\":\"35220021066\",\"attrWhlVal\":\"차콜,95,베이지,95\",\"arsBroadSeq\":66,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,베이지,95\",\"attrPrdCd\":35220021066},{\"attrPrdRepCd\":\"35220021067\",\"attrWhlVal\":\"차콜,95,베이지,100\",\"arsBroadSeq\":67,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,베이지,100\",\"attrPrdCd\":35220021067},{\"attrPrdRepCd\":\"35220021068\",\"attrWhlVal\":\"차콜,95,베이지,105\",\"arsBroadSeq\":68,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,베이지,105\",\"attrPrdCd\":35220021068},{\"attrPrdRepCd\":\"35220021069\",\"attrWhlVal\":\"차콜,95,멜란지그레이,90\",\"arsBroadSeq\":69,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,멜란지그레이,90\",\"attrPrdCd\":35220021069},{\"attrPrdRepCd\":\"35220021070\",\"attrWhlVal\":\"차콜,95,멜란지그레이,95\",\"arsBroadSeq\":70,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,멜란지그레이,95\",\"attrPrdCd\":35220021070},{\"attrPrdRepCd\":\"35220021071\",\"attrWhlVal\":\"차콜,95,멜란지그레이,100\",\"arsBroadSeq\":71,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,멜란지그레이,100\",\"attrPrdCd\":35220021071},{\"attrPrdRepCd\":\"35220021072\",\"attrWhlVal\":\"차콜,95,멜란지그레이,105\",\"arsBroadSeq\":72,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,95,멜란지그레이,105\",\"attrPrdCd\":35220021072},{\"attrPrdRepCd\":\"35220021073\",\"attrWhlVal\":\"차콜,100,블랙,90\",\"arsBroadSeq\":73,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,블랙,90\",\"attrPrdCd\":35220021073},{\"attrPrdRepCd\":\"35220021074\",\"attrWhlVal\":\"차콜,100,블랙,95\",\"arsBroadSeq\":74,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,블랙,95\",\"attrPrdCd\":35220021074},{\"attrPrdRepCd\":\"35220021075\",\"attrWhlVal\":\"차콜,100,블랙,100\",\"arsBroadSeq\":75,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,블랙,100\",\"attrPrdCd\":35220021075},{\"attrPrdRepCd\":\"35220021076\",\"attrWhlVal\":\"차콜,100,블랙,105\",\"arsBroadSeq\":76,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,블랙,105\",\"attrPrdCd\":35220021076},{\"attrPrdRepCd\":\"35220021077\",\"attrWhlVal\":\"차콜,100,베이지,90\",\"arsBroadSeq\":77,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,베이지,90\",\"attrPrdCd\":35220021077},{\"attrPrdRepCd\":\"35220021078\",\"attrWhlVal\":\"차콜,100,베이지,95\",\"arsBroadSeq\":78,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,베이지,95\",\"attrPrdCd\":35220021078},{\"attrPrdRepCd\":\"35220021079\",\"attrWhlVal\":\"차콜,100,베이지,100\",\"arsBroadSeq\":79,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,베이지,100\",\"attrPrdCd\":35220021079},{\"attrPrdRepCd\":\"35220021080\",\"attrWhlVal\":\"차콜,100,베이지,105\",\"arsBroadSeq\":80,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,베이지,105\",\"attrPrdCd\":35220021080},{\"attrPrdRepCd\":\"35220021081\",\"attrWhlVal\":\"차콜,100,멜란지그레이,90\",\"arsBroadSeq\":81,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,멜란지그레이,90\",\"attrPrdCd\":35220021081},{\"attrPrdRepCd\":\"35220021082\",\"attrWhlVal\":\"차콜,100,멜란지그레이,95\",\"arsBroadSeq\":82,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,멜란지그레이,95\",\"attrPrdCd\":35220021082},{\"attrPrdRepCd\":\"35220021083\",\"attrWhlVal\":\"차콜,100,멜란지그레이,100\",\"arsBroadSeq\":83,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,멜란지그레이,100\",\"attrPrdCd\":35220021083},{\"attrPrdRepCd\":\"35220021084\",\"attrWhlVal\":\"차콜,100,멜란지그레이,105\",\"arsBroadSeq\":84,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,100,멜란지그레이,105\",\"attrPrdCd\":35220021084},{\"attrPrdRepCd\":\"35220021085\",\"attrWhlVal\":\"차콜,105,블랙,90\",\"arsBroadSeq\":85,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,블랙,90\",\"attrPrdCd\":35220021085},{\"attrPrdRepCd\":\"35220021086\",\"attrWhlVal\":\"차콜,105,블랙,95\",\"arsBroadSeq\":86,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,블랙,95\",\"attrPrdCd\":35220021086},{\"attrPrdRepCd\":\"35220021087\",\"attrWhlVal\":\"차콜,105,블랙,100\",\"arsBroadSeq\":87,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,블랙,100\",\"attrPrdCd\":35220021087},{\"attrPrdRepCd\":\"35220021088\",\"attrWhlVal\":\"차콜,105,블랙,105\",\"arsBroadSeq\":88,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,블랙,105\",\"attrPrdCd\":35220021088},{\"attrPrdRepCd\":\"35220021089\",\"attrWhlVal\":\"차콜,105,베이지,90\",\"arsBroadSeq\":89,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,베이지,90\",\"attrPrdCd\":35220021089},{\"attrPrdRepCd\":\"35220021090\",\"attrWhlVal\":\"차콜,105,베이지,95\",\"arsBroadSeq\":90,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,베이지,95\",\"attrPrdCd\":35220021090},{\"attrPrdRepCd\":\"35220021091\",\"attrWhlVal\":\"차콜,105,베이지,100\",\"arsBroadSeq\":91,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,베이지,100\",\"attrPrdCd\":35220021091},{\"attrPrdRepCd\":\"35220021092\",\"attrWhlVal\":\"차콜,105,베이지,105\",\"arsBroadSeq\":92,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,베이지,105\",\"attrPrdCd\":35220021092},{\"attrPrdRepCd\":\"35220021093\",\"attrWhlVal\":\"차콜,105,멜란지그레이,90\",\"arsBroadSeq\":93,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,멜란지그레이,90\",\"attrPrdCd\":35220021093},{\"attrPrdRepCd\":\"35220021094\",\"attrWhlVal\":\"차콜,105,멜란지그레이,95\",\"arsBroadSeq\":94,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,멜란지그레이,95\",\"attrPrdCd\":35220021094},{\"attrPrdRepCd\":\"35220021095\",\"attrWhlVal\":\"차콜,105,멜란지그레이,100\",\"arsBroadSeq\":95,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,멜란지그레이,100\",\"attrPrdCd\":35220021095},{\"attrPrdRepCd\":\"35220021096\",\"attrWhlVal\":\"차콜,105,멜란지그레이,105\",\"arsBroadSeq\":96,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"차콜,105,멜란지그레이,105\",\"attrPrdCd\":35220021096},{\"attrPrdRepCd\":\"35220021097\",\"attrWhlVal\":\"라이트그레이,90,블랙,90\",\"arsBroadSeq\":97,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,블랙,90\",\"attrPrdCd\":35220021097},{\"attrPrdRepCd\":\"35220021098\",\"attrWhlVal\":\"라이트그레이,90,블랙,95\",\"arsBroadSeq\":98,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,블랙,95\",\"attrPrdCd\":35220021098},{\"attrPrdRepCd\":\"35220021099\",\"attrWhlVal\":\"라이트그레이,90,블랙,100\",\"arsBroadSeq\":99,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,블랙,100\",\"attrPrdCd\":35220021099},{\"attrPrdRepCd\":\"35220021100\",\"attrWhlVal\":\"라이트그레이,90,블랙,105\",\"arsBroadSeq\":100,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,블랙,105\",\"attrPrdCd\":35220021100},{\"attrPrdRepCd\":\"35220021101\",\"attrWhlVal\":\"라이트그레이,90,베이지,90\",\"arsBroadSeq\":101,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,베이지,90\",\"attrPrdCd\":35220021101},{\"attrPrdRepCd\":\"35220021102\",\"attrWhlVal\":\"라이트그레이,90,베이지,95\",\"arsBroadSeq\":102,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,베이지,95\",\"attrPrdCd\":35220021102},{\"attrPrdRepCd\":\"35220021103\",\"attrWhlVal\":\"라이트그레이,90,베이지,100\",\"arsBroadSeq\":103,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,베이지,100\",\"attrPrdCd\":35220021103},{\"attrPrdRepCd\":\"35220021104\",\"attrWhlVal\":\"라이트그레이,90,베이지,105\",\"arsBroadSeq\":104,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,베이지,105\",\"attrPrdCd\":35220021104},{\"attrPrdRepCd\":\"35220021105\",\"attrWhlVal\":\"라이트그레이,90,멜란지그레이,90\",\"arsBroadSeq\":105,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,멜란지그레이,90\",\"attrPrdCd\":35220021105},{\"attrPrdRepCd\":\"35220021106\",\"attrWhlVal\":\"라이트그레이,90,멜란지그레이,95\",\"arsBroadSeq\":106,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,멜란지그레이,95\",\"attrPrdCd\":35220021106},{\"attrPrdRepCd\":\"35220021107\",\"attrWhlVal\":\"라이트그레이,90,멜란지그레이,100\",\"arsBroadSeq\":107,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,멜란지그레이,100\",\"attrPrdCd\":35220021107},{\"attrPrdRepCd\":\"35220021108\",\"attrWhlVal\":\"라이트그레이,90,멜란지그레이,105\",\"arsBroadSeq\":108,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,90,멜란지그레이,105\",\"attrPrdCd\":35220021108},{\"attrPrdRepCd\":\"35220021109\",\"attrWhlVal\":\"라이트그레이,95,블랙,90\",\"arsBroadSeq\":109,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,블랙,90\",\"attrPrdCd\":35220021109},{\"attrPrdRepCd\":\"35220021110\",\"attrWhlVal\":\"라이트그레이,95,블랙,95\",\"arsBroadSeq\":110,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,블랙,95\",\"attrPrdCd\":35220021110},{\"attrPrdRepCd\":\"35220021111\",\"attrWhlVal\":\"라이트그레이,95,블랙,100\",\"arsBroadSeq\":111,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,블랙,100\",\"attrPrdCd\":35220021111},{\"attrPrdRepCd\":\"35220021112\",\"attrWhlVal\":\"라이트그레이,95,블랙,105\",\"arsBroadSeq\":112,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,블랙,105\",\"attrPrdCd\":35220021112},{\"attrPrdRepCd\":\"35220021113\",\"attrWhlVal\":\"라이트그레이,95,베이지,90\",\"arsBroadSeq\":113,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,베이지,90\",\"attrPrdCd\":35220021113},{\"attrPrdRepCd\":\"35220021114\",\"attrWhlVal\":\"라이트그레이,95,베이지,95\",\"arsBroadSeq\":114,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,베이지,95\",\"attrPrdCd\":35220021114},{\"attrPrdRepCd\":\"35220021115\",\"attrWhlVal\":\"라이트그레이,95,베이지,100\",\"arsBroadSeq\":115,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,베이지,100\",\"attrPrdCd\":35220021115},{\"attrPrdRepCd\":\"35220021116\",\"attrWhlVal\":\"라이트그레이,95,베이지,105\",\"arsBroadSeq\":116,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,베이지,105\",\"attrPrdCd\":35220021116},{\"attrPrdRepCd\":\"35220021117\",\"attrWhlVal\":\"라이트그레이,95,멜란지그레이,90\",\"arsBroadSeq\":117,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,멜란지그레이,90\",\"attrPrdCd\":35220021117},{\"attrPrdRepCd\":\"35220021118\",\"attrWhlVal\":\"라이트그레이,95,멜란지그레이,95\",\"arsBroadSeq\":118,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,멜란지그레이,95\",\"attrPrdCd\":35220021118},{\"attrPrdRepCd\":\"35220021119\",\"attrWhlVal\":\"라이트그레이,95,멜란지그레이,100\",\"arsBroadSeq\":119,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,멜란지그레이,100\",\"attrPrdCd\":35220021119},{\"attrPrdRepCd\":\"35220021120\",\"attrWhlVal\":\"라이트그레이,95,멜란지그레이,105\",\"arsBroadSeq\":120,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,95,멜란지그레이,105\",\"attrPrdCd\":35220021120},{\"attrPrdRepCd\":\"35220021121\",\"attrWhlVal\":\"라이트그레이,100,블랙,90\",\"arsBroadSeq\":121,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,블랙,90\",\"attrPrdCd\":35220021121},{\"attrPrdRepCd\":\"35220021122\",\"attrWhlVal\":\"라이트그레이,100,블랙,95\",\"arsBroadSeq\":122,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,블랙,95\",\"attrPrdCd\":35220021122},{\"attrPrdRepCd\":\"35220021123\",\"attrWhlVal\":\"라이트그레이,100,블랙,100\",\"arsBroadSeq\":123,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,블랙,100\",\"attrPrdCd\":35220021123},{\"attrPrdRepCd\":\"35220021124\",\"attrWhlVal\":\"라이트그레이,100,블랙,105\",\"arsBroadSeq\":124,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,블랙,105\",\"attrPrdCd\":35220021124},{\"attrPrdRepCd\":\"35220021125\",\"attrWhlVal\":\"라이트그레이,100,베이지,90\",\"arsBroadSeq\":125,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,베이지,90\",\"attrPrdCd\":35220021125},{\"attrPrdRepCd\":\"35220021126\",\"attrWhlVal\":\"라이트그레이,100,베이지,95\",\"arsBroadSeq\":126,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,베이지,95\",\"attrPrdCd\":35220021126},{\"attrPrdRepCd\":\"35220021127\",\"attrWhlVal\":\"라이트그레이,100,베이지,100\",\"arsBroadSeq\":127,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,베이지,100\",\"attrPrdCd\":35220021127},{\"attrPrdRepCd\":\"35220021128\",\"attrWhlVal\":\"라이트그레이,100,베이지,105\",\"arsBroadSeq\":128,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,베이지,105\",\"attrPrdCd\":35220021128},{\"attrPrdRepCd\":\"35220021129\",\"attrWhlVal\":\"라이트그레이,100,멜란지그레이,90\",\"arsBroadSeq\":129,\"ordPsblQty\":9998,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,멜란지그레이,90\",\"attrPrdCd\":35220021129},{\"attrPrdRepCd\":\"35220021130\",\"attrWhlVal\":\"라이트그레이,100,멜란지그레이,95\",\"arsBroadSeq\":130,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,멜란지그레이,95\",\"attrPrdCd\":35220021130},{\"attrPrdRepCd\":\"35220021131\",\"attrWhlVal\":\"라이트그레이,100,멜란지그레이,100\",\"arsBroadSeq\":131,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,멜란지그레이,100\",\"attrPrdCd\":35220021131},{\"attrPrdRepCd\":\"35220021132\",\"attrWhlVal\":\"라이트그레이,100,멜란지그레이,105\",\"arsBroadSeq\":132,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,100,멜란지그레이,105\",\"attrPrdCd\":35220021132},{\"attrPrdRepCd\":\"35220021133\",\"attrWhlVal\":\"라이트그레이,105,블랙,90\",\"arsBroadSeq\":133,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,블랙,90\",\"attrPrdCd\":35220021133},{\"attrPrdRepCd\":\"35220021134\",\"attrWhlVal\":\"라이트그레이,105,블랙,95\",\"arsBroadSeq\":134,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,블랙,95\",\"attrPrdCd\":35220021134},{\"attrPrdRepCd\":\"35220021135\",\"attrWhlVal\":\"라이트그레이,105,블랙,100\",\"arsBroadSeq\":135,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,블랙,100\",\"attrPrdCd\":35220021135},{\"attrPrdRepCd\":\"35220021136\",\"attrWhlVal\":\"라이트그레이,105,블랙,105\",\"arsBroadSeq\":136,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,블랙,105\",\"attrPrdCd\":35220021136},{\"attrPrdRepCd\":\"35220021137\",\"attrWhlVal\":\"라이트그레이,105,베이지,90\",\"arsBroadSeq\":137,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,베이지,90\",\"attrPrdCd\":35220021137},{\"attrPrdRepCd\":\"35220021138\",\"attrWhlVal\":\"라이트그레이,105,베이지,95\",\"arsBroadSeq\":138,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,베이지,95\",\"attrPrdCd\":35220021138},{\"attrPrdRepCd\":\"35220021139\",\"attrWhlVal\":\"라이트그레이,105,베이지,100\",\"arsBroadSeq\":139,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,베이지,100\",\"attrPrdCd\":35220021139},{\"attrPrdRepCd\":\"35220021140\",\"attrWhlVal\":\"라이트그레이,105,베이지,105\",\"arsBroadSeq\":140,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,베이지,105\",\"attrPrdCd\":35220021140},{\"attrPrdRepCd\":\"35220021141\",\"attrWhlVal\":\"라이트그레이,105,멜란지그레이,90\",\"arsBroadSeq\":141,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,멜란지그레이,90\",\"attrPrdCd\":35220021141},{\"attrPrdRepCd\":\"35220021142\",\"attrWhlVal\":\"라이트그레이,105,멜란지그레이,95\",\"arsBroadSeq\":142,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,멜란지그레이,95\",\"attrPrdCd\":35220021142},{\"attrPrdRepCd\":\"35220021143\",\"attrWhlVal\":\"라이트그레이,105,멜란지그레이,100\",\"arsBroadSeq\":143,\"ordPsblQty\":9999,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,멜란지그레이,100\",\"attrPrdCd\":35220021143},{\"attrPrdRepCd\":\"35220021144\",\"attrWhlVal\":\"라이트그레이,105,멜란지그레이,105\",\"arsBroadSeq\":144,\"ordPsblQty\":9997,\"soldOutYn\":\"N\",\"attrPrdExposNm\":\"라이트그레이,105,멜란지그레이,105\",\"attrPrdCd\":35220021144}]},\"outArsPrdAnswerInfo\":{\"values\":{\"attrTypNm2\":\"사이즈\",\"attrTypNm1\":\"라운드넥\",\"pageRange\":10,\"attrTypNm4\":\"사이즈\",\"attrTypNm3\":\"브이넥\",\"insuCoCd\":\" \",\"supCd\":\"1026281\",\"rsltCd\":\"00\",\"zrwonSaleYn\":\"N\",\"prdImgUrl\":\"http://image.gsshop.com/image/35/22/35220021_O1.jpg\",\"autoOrdClsCd\":\"N\",\"prdTypCd\":\"S\",\"chanlDtlCd\":\"CF\",\"chanlCd\":\"C\",\"rentPrdYn\":\"N\",\"attrGftYn\":\"Y\",\"dmSeqnoId\":\"N\",\"attrTypSeq3\":\"3\",\"arsMaxQty\":\"1\",\"attrTypSeq4\":\"4\",\"attrTypSeq1\":\"1\",\"autoOrdPrdNm\":\"폴햄 경량 구스다운 조끼\",\"attrTypSeq2\":\"2\",\"travlPrdYn\":\"N\",\"mnfcCoNm\":\"(주)엠에스디엔엠\",\"supNm\":\"(주)에이션패션\",\"gftcertUsePsblYn\":\"Y\",\"rentSalePrc\":\"0\",\"celphnPrdYn\":\"N\",\"rowsPerPage\":20,\"frmlesPrdTypCd\":\"N\",\"prdDtlUrl\":\"http://m.gsshop.com/prd/prdDescForKakao.gs?prdid=35220021\",\"pageIdx\":1,\"autoOrdPsblYn\":\"Y\",\"prdCd\":35220021,\"tempoutYn\":\"N\",\"exposPrdNm\":\"[폴햄] 19년 신상 구스다운 경량베스트 2종 세트\"}}},\"headerSet\":{\"timestamp1\":\"1591662119848\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"1e97d11a-ae9f-42cb-8883-2e80ba387d9f\",\"timestamp7\":\"2020-06-09 09:21:59.850\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkPrdQry/getTlkPrdAttrList/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 상품속성조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6021" : //상품사은품조회(필수,선택)
				path = "esbTlkPrdQry/getTlkPmoGftList";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsPrdPmoMandGiftList\":{\"values\":[]},\"outArsPrdPmoGiftInfo\":{\"values\":{\"pageRange\":10,\"pageIdx\":1,\"rsltCd\":\"23\",\"rowsPerPage\":20}},\"outArsPrdPmoSelGiftList\":{\"values\":[]}},\"headerSet\":{\"timestamp1\":\"1591682306290\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"922f68bc-5396-4adb-96ff-97352cc9f75e\",\"timestamp7\":\"2020-06-09 14:58:26.298\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6021\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkPrdQry/getTlkPmoGftList/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 상품사은품조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6022" : //상품재고차감
				path = "esbTlkPrdQry/modifyOrdPsblQty";
				restRtn = "";
				break;
			case "IFESBTLK6023" : //상품재고복원
				path = "esbTlkPrdQry/modifyOrdPsblQty";
				restRtn = "";
				break;
			case "IFESBTLK6030" : //고객정보조회
				path = "esbTlkCustInfoQry/getTlkCustCnf";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsCustCnfInfoList\":{\"values\":[{\"custNo\":12342311,\"ordPsblYn\":\"Y\",\"custNm\":\"김태엽\",\"dmCustGrdCd\":\"15\",\"mobilAppYn\":\"N\",\"ssnoPre7\":\"7608201\"},{\"custNo\":30665701,\"ordPsblYn\":\"Y\",\"custNm\":\"김현주\",\"dmCustGrdCd\":null,\"mobilAppYn\":\"N\",\"ssnoPre7\":\"8009052\"}]},\"outArsCustCnfAnswrInfo\":{\"values\":{\"reqArsEquipNo\":null,\"regCustCnt\":\"2\",\"rsltCd\":\"12\",\"reqArsLineNo\":null,\"tmpOrdCd\":null}}},\"headerSet\":{\"timestamp1\":\"1591682306588\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"82a0f80d-f790-49c7-b5ae-e00f31f2339c\",\"timestamp7\":\"2020-06-09 14:58:26.594\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6030\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkCustInfoQry/getTlkCustCnf/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 고객정보조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6031" : //고객등록정보조회
				path = "esbTlkCustInfoQry/getTlkCustInfo";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsCustInfoAnswrInfo\":{\"values\":{\"pageRange\":10,\"cardRegCnt\":\"3\",\"pageIdx\":1,\"rsltCd\":\"00\",\"gftcertAssetAmt\":100000,\"cashRcptIssueAgreeYn\":\"Y\",\"rowsPerPage\":20,\"gftcertNo\":\"371340525076\"}},\"outCustCardInfoList\":{\"values\":[{\"pageRange\":10,\"cardKindAffilCd\":\"WH26\",\"cardValidTerm\":\"0621\",\"cardNm\":\"신한\",\"pageIdx\":1,\"cardKindCd\":\"WH\",\"payMeanNo\":\"1040389423\",\"cardNo\":\"1050\",\"rowsPerPage\":20,\"repCardKindCd\":\"LG\"},{\"pageRange\":10,\"cardKindAffilCd\":\"KM04\",\"cardValidTerm\":\"0920\",\"cardNm\":\"KB국민\",\"pageIdx\":1,\"cardKindCd\":\"KM\",\"payMeanNo\":\"1040389293\",\"cardNo\":\"5015\",\"rowsPerPage\":20,\"repCardKindCd\":\"KM\"},{\"pageRange\":10,\"cardKindAffilCd\":\"BC12\",\"cardValidTerm\":\"0724\",\"cardNm\":\"비씨\",\"pageIdx\":1,\"cardKindCd\":\"BC\",\"payMeanNo\":\"1040388281\",\"cardNo\":\"8277\",\"rowsPerPage\":20,\"repCardKindCd\":\"BC\"}]}},\"headerSet\":{\"timestamp1\":\"1591682307067\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"c7e71359-a182-404b-b876-ebea4633ef6f\",\"timestamp7\":\"2020-06-09 14:58:27.074\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6031\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkCustInfoQry/getTlkCustInfo/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 고객등록정보조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6032" : //상품주문단가조회
				path = "esbTlkPrdQry/getTlkPrdUprcList";
				restRtn = "";
				break;
			case "IFESBTLK6034" : //배송지조회
				path = "esbTlkCustAddrInfoQry/getTlkCustDlvp";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsCustDlvpList\":{\"values\":[{\"addrGbnCd\":\"01\",\"addrCntcNo\":\"80895301\",\"dlvpAddr\":\"서울 강북구 삼양로19길 113 104동 403호(삼각산동 1357, 삼각산아이원아파트)\",\"dlvNoadmtRegonYn\":\"N\"},{\"addrGbnCd\":\"04\",\"addrCntcNo\":\"80900984\",\"dlvpAddr\":\"간편회원기본주소 미입력\",\"dlvNoadmtRegonYn\":\"Y\"},{\"addrGbnCd\":\"04\",\"addrCntcNo\":\"80895794\",\"dlvpAddr\":\"인천 옹진군 연평면 소연평로 16 1층(연평리 999-7)\",\"dlvNoadmtRegonYn\":\"N\"}]},\"outArsCustDlvpAnswrInfo\":{\"values\":{\"pageRange\":10,\"pageIdx\":1,\"rsltCd\":\"00\",\"dlvpAddrCnt\":3,\"tmpOrdCd\":\"531182760\",\"rowsPerPage\":20}}},\"headerSet\":{\"timestamp1\":\"1591682309599\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"65fcd89b-30f4-4e3a-bd5e-3da00e9c301c\",\"timestamp7\":\"2020-06-09 14:58:29.606\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6034\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkCustAddrInfoQry/getTlkCustDlvp/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 배송지조회\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6040" : //결제수단조회및부여
				path = "esbTlkPayMean/getTlkPayMeanList";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsTlkCardList\":{\"values\":[{\"cardKindAffilCd\":\"LGLG\",\"cardKindCd\":\"LG\",\"tlkCardGbnCd\":\"06\",\"repCardKindCd\":\"LG\",\"tlkCardNm\":\"신한카드\"},{\"cardKindAffilCd\":\"BCBC\",\"cardKindCd\":\"BC\",\"tlkCardGbnCd\":\"01\",\"repCardKindCd\":\"BC\",\"tlkCardNm\":\"BC카드\"},{\"cardKindAffilCd\":\"SSSS\",\"cardKindCd\":\"SS\",\"tlkCardGbnCd\":\"04\",\"repCardKindCd\":\"SS\",\"tlkCardNm\":\"삼성카드\"},{\"cardKindAffilCd\":\"DADA\",\"cardKindCd\":\"DA\",\"tlkCardGbnCd\":\"07\",\"repCardKindCd\":\"DA\",\"tlkCardNm\":\"현대카드\"},{\"cardKindAffilCd\":\"BC20\",\"cardKindCd\":\"BC\",\"tlkCardGbnCd\":\"15\",\"repCardKindCd\":\"BC\",\"tlkCardNm\":\"우리카드\"},{\"cardKindAffilCd\":\"KM04\",\"cardKindCd\":\"KM\",\"tlkCardGbnCd\":\"02\",\"repCardKindCd\":\"KM\",\"tlkCardNm\":\"KB카드\"},{\"cardKindAffilCd\":\"KM11\",\"cardKindCd\":\"KM\",\"tlkCardGbnCd\":\"12\",\"repCardKindCd\":\"NH\",\"tlkCardNm\":\"농협카드\"},{\"cardKindAffilCd\":\"WH81\",\"cardKindCd\":\"WH\",\"tlkCardGbnCd\":\"16\",\"repCardKindCd\":\"HN\",\"tlkCardNm\":\"하나카드\"},{\"cardKindAffilCd\":\"KM53\",\"cardKindCd\":\"KM\",\"tlkCardGbnCd\":\"11\",\"repCardKindCd\":\"CT\",\"tlkCardNm\":\"시티카드\"},{\"cardKindAffilCd\":\"AXAX\",\"cardKindCd\":\"AX\",\"tlkCardGbnCd\":\"08\",\"repCardKindCd\":\"AX\",\"tlkCardNm\":\"롯데카드\"},{\"cardKindAffilCd\":\"WH34\",\"cardKindCd\":\"WH\",\"tlkCardGbnCd\":\"21\",\"repCardKindCd\":\"KJ\",\"tlkCardNm\":\"광주VISA\"},{\"cardKindAffilCd\":\"WH07\",\"cardKindCd\":\"WH\",\"tlkCardGbnCd\":\"13\",\"repCardKindCd\":\"SH\",\"tlkCardNm\":\"수협VISA\"},{\"cardKindAffilCd\":\"WH37\",\"cardKindCd\":\"WH\",\"tlkCardGbnCd\":\"22\",\"repCardKindCd\":\"JB\",\"tlkCardNm\":\"전북VISA\"},{\"cardKindAffilCd\":\"WH35\",\"cardKindCd\":\"WH\",\"tlkCardGbnCd\":\"23\",\"repCardKindCd\":\"JJ\",\"tlkCardNm\":\"제주VISA\"}]},\"outArsExcluAccntRslt\":{\"values\":{\"pageRange\":10,\"pageIdx\":1,\"rsltCd\":\"00\",\"dpoPsblDt\":\"20200611\",\"rowsPerPage\":20}},\"outArsExcluAccntList\":{\"values\":[{\"primPayMeanYn\":\"Y\",\"payMeanNo\":\"1040389297\",\"bankCd\":\"PV\",\"accntNo\":\"810453-80-001598\",\"bankNm\":\"우체국(전용계좌)\",\"excluAccntGivYn\":\"Y\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"HS\",\"accntNo\":null,\"bankNm\":\"SC제일은행(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"HV\",\"accntNo\":null,\"bankNm\":\"KEB하나은행(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":\"1040389504\",\"bankCd\":\"N2\",\"accntNo\":\"593590-85-808553\",\"bankNm\":\"국민은행(전용계좌)\",\"excluAccntGivYn\":\"Y\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"N4\",\"accntNo\":null,\"bankNm\":\"우리은행(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":\"1040389298\",\"bankCd\":\"N3\",\"accntNo\":\"562134-01-404707\",\"bankNm\":\"신한은행(전용계좌)\",\"excluAccntGivYn\":\"Y\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"N1\",\"accntNo\":null,\"bankNm\":\"농협(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"HP\",\"accntNo\":null,\"bankNm\":\"부산은행(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"N5\",\"accntNo\":null,\"bankNm\":\"기업은행(전용계좌)\",\"excluAccntGivYn\":\"N\"},{\"primPayMeanYn\":\"N\",\"payMeanNo\":null,\"bankCd\":\"DG\",\"accntNo\":null,\"bankNm\":\"대구은행(전용계좌)\",\"excluAccntGivYn\":\"N\"}]}},\"headerSet\":{\"timestamp1\":\"1591682325974\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"6e94cd37-a08d-4dcd-9c72-5ac27e3b9536\",\"timestamp7\":\"2020-06-09 14:58:45.981\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6040\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbTlkPayMean/getTlkPayMeanList/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 결제수단조회및부여\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":10000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6041" : //무이자여부및청구할인조회
				path = "esbTlkPayMean/getTlkCardNoIntList";
				restRtn = "";
				break;
			case "IFESBTLK6042" : //주문생성결제
				path = "esbOrdProc/addTlkOrd";
				restRtn = "{\"errorCode\":0,\"variableList\":null,\"datasetList\":{\"outArsOrdBaseRes\":{\"values\":{\"sessionUserId\":\"TALK\",\"arsChanlCd\":\"34\",\"pageRange\":10,\"custNo\":\"12342311\",\"zrwonSaleYn\":\"N\",\"sessionChanlCd\":\"TLK\",\"dlvpRegYn\":\"N\",\"rowsPerPage\":20,\"chanlCd\":\"C\",\"payMthodCd\":\"2\",\"retCd\":\"E\",\"pageIdx\":1,\"dlvpAddrNo\":\"80895301\",\"retDtlCd\":\"96\"}}},\"headerSet\":{\"timestamp1\":\"1591682330109\",\"timestamp5\":\"\",\"timestamp4\":\"\",\"timestamp3\":\"\",\"timestamp2\":\"\",\"route\":{\"fullUrlRsp\":\"http://10.52.221.34:18101\",\"esbDomainNm\":\"Common\",\"esbAddr\":\"10.52.221.34\",\"fullUrl\":\"http://10.52.221.34:18101/json/esb\",\"esbPort\":\"18101\",\"esbServiceNm\":\"json/esb\"},\"esbRespMsg\":\"\",\"timestamp8\":\"\",\"esbRespCode\":\"\",\"msgKey\":\"b8440e84-6a95-4616-8219-fbec4a00d098\",\"timestamp7\":\"2020-06-09 14:58:50.120\",\"interfaceInfo\":{\"ifType\":\"COMMON_ROUTE\",\"consumerId\":\"TLK\",\"currRetryNo\":0,\"ifId\":\"IFESBTLK6042\",\"encryptYn\":\"N\",\"providerServiceId\":\"esbOrdProc/addTlkOrd/10/rspService.gs\",\"repositoryStage\":\"QA\",\"encryptKey\":\"\",\"ifNm\":\"(카카오톡) 주문생성결제\",\"domainId\":\"Common\",\"command\":\"REQ\",\"timeout\":30000,\"guaranteeYn\":\"N\",\"maxRetryNo\":1,\"providerId\":\"SEC\",\"domainServiceId\":\"json/esb\",\"logYn\":\"N\",\"syncType\":\"S\"},\"timestamp6\":\"\",\"successYn\":\"Y\",\"config\":{\"esbRuntimeStage\":\"QA\",\"esbHeaderVer\":\"1.0\",\"esbHeaderClassNm\":\"smart4c.esb.ifhub.common.msg.header.EsbHeaderVer0100\"},\"consumer\":{\"consumerType\":\"JAVA\",\"consumerMsgFormat\":\"JSON\",\"consumerNm\":\"톡주문\",\"consumerProtocol\":\"HTTP\",\"consumerApiEnv\":\"1\",\"consumerId\":\"TLK\",\"consumerIp\":\"10.52.220.149\",\"consumerEncode\":\"UTF-8\"}},\"errorMsg\":\"success\"}";
				break;
			case "IFESBTLK6043" : //카카오페이인증조회
				path = "ordPayProc/getTalkKakaoPayTxnId";
				restRtn = "";
				break;
			case "IFESBTLK6050" : //보험예약접수
				path = "esbOrdCmm/addTlkInsuAdmConslInfo";
				restRtn = "";
				break;
			case "IFESBTLK6060" : //렌탈예약접수
				path = "esbOrdCmm/addTlkConslRsrvInfo";
				restRtn = "";
				break;
			case "IFESBTLK6310" : //신규카드등록
				path = "esbTlkPayMean/saveTlkPayMeanCard";
				restRtn = "";
				break;
			case "IFESBTLK7311" : //고객배송지주소등록
				path = "esbTlkCustAddrInfoQry/addTlkCustDlvp";
				restRtn = "";
				break;
			case "IFESBTLK7312" : //주문취소반품가능목록조회
				path = "esbTlkInqryProc/getTlkCnlRtpPsblList";
				restRtn = "";
				break;
			case "IFESBTLK7313" : //주문취소반품요청
				path = "esbTlkInqryProc/tlkOrdCnlRtpProc";
				restRtn = "";
				break;
			default :
				break;
		}
		
		if( API_USE ) {
			HttpHeaders headers = new HttpHeaders();
			URI apiUri = UriComponentsBuilder.newInstance()
						.scheme(API_SCHEME)
		                .host(API_CENTER_HOST)
		                .port(API_PORT)
		                .path(path+".gs")
		                .buildAndExpand("")
		                .toUri();

			HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
			restRtn = restTemplate.exchange(apiUri.toString(), HttpMethod.POST, httpEntity, String.class, paramMap).getBody();
		}

		JSONParser jsonParser = new JSONParser();
        //JSON데이터를 넣어 JSON Object 로 만들어 준다.
        JSONObject jsonObject = (JSONObject) jsonParser.parse(restRtn);


		return restRtn;
	}


}
