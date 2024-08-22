# OpenAPI definition


**Description**:OpenAPI definition


**HOST**:http://localhost:8080


**Contacts**:


**Version**:v0


**URL**:/v3/api-docs


[TOC]






# User


## add new user


**url**:`/admin/user/`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "username": "",
  "name": "",
  "password": "",
  "phone": "",
  "sex": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|UserDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;sex|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## update


**url**:`/admin/user/`


**method**:`PUT`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "username": "",
  "name": "",
  "password": "",
  "phone": "",
  "sex": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|UserDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;sex|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## startOrStop


**url**:`/admin/user/status/{status}`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|status||path|true|integer(int32)||
|id||query|true|integer(int32)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## login


**url**:`/admin/user/login`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "username": "",
  "password": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userLoginDTO|login DTO|body|true|UserLoginDTO|UserLoginDTO|
|&emsp;&emsp;username|username||false|string||
|&emsp;&emsp;password|password||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultUserVO|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||UserVO|UserVO|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;userName||string||
|&emsp;&emsp;name||string||
|&emsp;&emsp;token||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {
		"id": 0,
		"userName": "",
		"name": "",
		"token": ""
	}
}
```


## getById


**url**:`/admin/user/{id}`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int32)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultUser|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||User|User|
|&emsp;&emsp;id||integer(int32)||
|&emsp;&emsp;username||string||
|&emsp;&emsp;name||string||
|&emsp;&emsp;password||string||
|&emsp;&emsp;phone||string||
|&emsp;&emsp;sex||string||
|&emsp;&emsp;idNumber||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createUser||integer(int32)||
|&emsp;&emsp;updateUser||integer(int32)||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {
		"id": 0,
		"username": "",
		"name": "",
		"password": "",
		"phone": "",
		"sex": "",
		"idNumber": "",
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createUser": 0,
		"updateUser": 0
	}
}
```


## page


**url**:`/admin/user/page`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userPageQueryDTO||query|true|UserPageQueryDTO|UserPageQueryDTO|
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;page|||false|integer(int32)||
|&emsp;&emsp;pageSize|||false|integer(int32)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageResult|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||PageResult|PageResult|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;records||array|object|


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {
		"total": 0,
		"records": []
	}
}
```


# vertica


## UPSTracking


**url**:`/admin/vertica/ups`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "trackingId": [],
  "isActive": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parcelTrackingDTO|ParcelTrackDTO|body|true|ParcelTrackingDTO|ParcelTrackingDTO|
|&emsp;&emsp;trackingId|||false|array|string|
|&emsp;&emsp;isActive|||false|integer(int32)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListUPSTracking|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|UPSTracking|
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;estimatedDelivery||string||
|&emsp;&emsp;eventStatusCode||string||
|&emsp;&emsp;eventStatusDescription||string||
|&emsp;&emsp;eventLocationCity||string||
|&emsp;&emsp;eventDateTime||string||
|&emsp;&emsp;shippedTs||string||
|&emsp;&emsp;actualDeliveredTs||string||
|&emsp;&emsp;originalScanTs||string||
|&emsp;&emsp;outForDeliveryTs||string||
|&emsp;&emsp;isActive||integer(int32)||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"trackingId": "",
			"estimatedDelivery": "",
			"eventStatusCode": "",
			"eventStatusDescription": "",
			"eventLocationCity": "",
			"eventDateTime": "",
			"shippedTs": "",
			"actualDeliveredTs": "",
			"originalScanTs": "",
			"outForDeliveryTs": "",
			"isActive": 0
		}
	]
}
```


## FedExTracking


**url**:`/admin/vertica/fedex`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "trackingId": [],
  "isActive": 0
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parcelTrackingDTO|ParcelTrackDTO|body|true|ParcelTrackingDTO|ParcelTrackingDTO|
|&emsp;&emsp;trackingId|||false|array|string|
|&emsp;&emsp;isActive|||false|integer(int32)||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListFedExTracking|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|FedExTracking|
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;statusdetailTs||string||
|&emsp;&emsp;statusdetailDescription||string||
|&emsp;&emsp;serviceType||string||
|&emsp;&emsp;createdTs||string||
|&emsp;&emsp;eventTs||string||
|&emsp;&emsp;eventsDescription||string||
|&emsp;&emsp;statusCity||string||
|&emsp;&emsp;shipmentDelayCity||string||
|&emsp;&emsp;shipmentException||string||
|&emsp;&emsp;exceptionDescription||string||
|&emsp;&emsp;returnTrackingNumberNew||string||
|&emsp;&emsp;isActive||integer(int32)||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"trackingId": "",
			"statusdetailTs": "",
			"statusdetailDescription": "",
			"serviceType": "",
			"createdTs": "",
			"eventTs": "",
			"eventsDescription": "",
			"statusCity": "",
			"shipmentDelayCity": "",
			"shipmentException": "",
			"exceptionDescription": "",
			"returnTrackingNumberNew": "",
			"isActive": 0
		}
	]
}
```


## EDD


**url**:`/admin/vertica/edd`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "idList": [],
  "searchFlag": ""
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|eDDDTO|EDD|body|true|EDDDTO|EDDDTO|
|&emsp;&emsp;idList|||false|array|string|
|&emsp;&emsp;searchFlag|||false|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListEDD|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|EDD|
|&emsp;&emsp;poId||string||
|&emsp;&emsp;doId||string||
|&emsp;&emsp;shippedSku||string||
|&emsp;&emsp;shippingAmt||string||
|&emsp;&emsp;shippedQty||string||
|&emsp;&emsp;physicalFulfillerId||string||
|&emsp;&emsp;tracking||string||
|&emsp;&emsp;orderEntryDate||string||
|&emsp;&emsp;shipScanDate||string||
|&emsp;&emsp;deliveredTs||string||
|&emsp;&emsp;fulfillmentCarrierId||string||
|&emsp;&emsp;salesPrice||string||
|&emsp;&emsp;listPrice||string||
|&emsp;&emsp;cancellationStatus||string||
|&emsp;&emsp;cancellationReason||string||
|&emsp;&emsp;orderTsEst||string||
|&emsp;&emsp;lineItemStatus||string||
|&emsp;&emsp;orderStatus||string||
|&emsp;&emsp;edd||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"poId": "",
			"doId": "",
			"shippedSku": "",
			"shippingAmt": "",
			"shippedQty": "",
			"physicalFulfillerId": "",
			"tracking": "",
			"orderEntryDate": "",
			"shipScanDate": "",
			"deliveredTs": "",
			"fulfillmentCarrierId": "",
			"salesPrice": "",
			"listPrice": "",
			"cancellationStatus": "",
			"cancellationReason": "",
			"orderTsEst": "",
			"lineItemStatus": "",
			"orderStatus": "",
			"edd": ""
		}
	]
}
```


## test


**url**:`/admin/vertica/test`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


暂无


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK||


**Response Params**:


None


**Response Example**:
```javascript

```


## PBShip


**url**:`/admin/vertica/pbShip`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|start||query|true|string||
|end||query|true|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListPBTracking|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|PBTracking|
|&emsp;&emsp;trackingIdLong||string||
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;productId||string||
|&emsp;&emsp;poId||string||
|&emsp;&emsp;fulfillmentDeliveryDoId||string||
|&emsp;&emsp;pickedupTs||string||
|&emsp;&emsp;expectedDeliveryTs||string||
|&emsp;&emsp;fulfillmentCarrierId||string||
|&emsp;&emsp;shipmentStatus||string||
|&emsp;&emsp;shipDateTs||string||
|&emsp;&emsp;deliveredTs||string||
|&emsp;&emsp;originalPromiseDeliveryTs||string||
|&emsp;&emsp;currentPromiseDeliveryTs||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"trackingIdLong": "",
			"trackingId": "",
			"productId": "",
			"poId": "",
			"fulfillmentDeliveryDoId": "",
			"pickedupTs": "",
			"expectedDeliveryTs": "",
			"fulfillmentCarrierId": "",
			"shipmentStatus": "",
			"shipDateTs": "",
			"deliveredTs": "",
			"originalPromiseDeliveryTs": "",
			"currentPromiseDeliveryTs": ""
		}
	]
}
```


## PBShipSummary


**url**:`/admin/vertica/pbShipSummary`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|start||query|true|string||
|end||query|true|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultInteger|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||integer(int32)|integer(int32)|


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": 0
}
```


## PBDelivery


**url**:`/admin/vertica/pbDelivered`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|start||query|true|string||
|end||query|true|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListPBTracking|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|PBTracking|
|&emsp;&emsp;trackingIdLong||string||
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;productId||string||
|&emsp;&emsp;poId||string||
|&emsp;&emsp;fulfillmentDeliveryDoId||string||
|&emsp;&emsp;pickedupTs||string||
|&emsp;&emsp;expectedDeliveryTs||string||
|&emsp;&emsp;fulfillmentCarrierId||string||
|&emsp;&emsp;shipmentStatus||string||
|&emsp;&emsp;shipDateTs||string||
|&emsp;&emsp;deliveredTs||string||
|&emsp;&emsp;originalPromiseDeliveryTs||string||
|&emsp;&emsp;currentPromiseDeliveryTs||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"trackingIdLong": "",
			"trackingId": "",
			"productId": "",
			"poId": "",
			"fulfillmentDeliveryDoId": "",
			"pickedupTs": "",
			"expectedDeliveryTs": "",
			"fulfillmentCarrierId": "",
			"shipmentStatus": "",
			"shipDateTs": "",
			"deliveredTs": "",
			"originalPromiseDeliveryTs": "",
			"currentPromiseDeliveryTs": ""
		}
	]
}
```


## PBDeliverySummary


**url**:`/admin/vertica/pbDeliveredSummary`


**method**:`GET`


**produces**:`application/x-www-form-urlencoded`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|start||query|true|string||
|end||query|true|string||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultInteger|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||integer(int32)|integer(int32)|


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": 0
}
```


# ReturnSearch


## upload


**url**:`/admin/ageReturn/upload`


**method**:`POST`


**produces**:`multipart/form-data`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## uploadSimple


**url**:`/admin/ageReturn/uploadSimple`


**method**:`POST`


**produces**:`multipart/form-data`


**consumes**:`*/*`


**Note**:


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|Result|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||object||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": {}
}
```


## searchByRDO


**url**:`/admin/ageReturn/byRDO`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "rdo": []
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchByRDODTO|searchByDTO|body|true|SearchByRDODTO|SearchByRDODTO|
|&emsp;&emsp;rdo|||false|array|string|


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListReturn|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|Return|
|&emsp;&emsp;poLookupKey||string||
|&emsp;&emsp;rdoId||string||
|&emsp;&emsp;returnTrackingId||string||
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;sku||string||
|&emsp;&emsp;rmaNumber||string||
|&emsp;&emsp;soId||string||
|&emsp;&emsp;poId||string||
|&emsp;&emsp;lineItemDetailedStatus||string||
|&emsp;&emsp;rsoId||string||
|&emsp;&emsp;channel||string||
|&emsp;&emsp;currentRefundStatus||string||
|&emsp;&emsp;labelGeneratedTs||string||
|&emsp;&emsp;refundDt||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"poLookupKey": "",
			"rdoId": "",
			"returnTrackingId": "",
			"trackingId": "",
			"sku": "",
			"rmaNumber": "",
			"soId": "",
			"poId": "",
			"lineItemDetailedStatus": "",
			"rsoId": "",
			"channel": "",
			"currentRefundStatus": "",
			"labelGeneratedTs": "",
			"refundDt": ""
		}
	]
}
```


## searchByRDOSimple


**url**:`/admin/ageReturn/byRDOSimple`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "rdo": []
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchByRDODTO|searchByDTO|body|true|SearchByRDODTO|SearchByRDODTO|
|&emsp;&emsp;rdo|||false|array|string|


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListReturnSimple|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|ReturnSimple|
|&emsp;&emsp;rdo||string||
|&emsp;&emsp;trackingNumber||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"rdo": "",
			"trackingNumber": ""
		}
	]
}
```


## searchByPO


**url**:`/admin/ageReturn/byPO`


**method**:`POST`


**produces**:`application/x-www-form-urlencoded,application/json`


**consumes**:`*/*`


**Note**:


**Example**:


```javascript
{
  "rdo": []
}
```


**Params**:


| name | description | in    | require | type | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|searchByRDODTO|searchByDTO|body|true|SearchByRDODTO|SearchByRDODTO|
|&emsp;&emsp;rdo|||false|array|string|


**Status**:


| code | description | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListReturn|


**Response Params**:


| name | description | type | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|msg||string||
|data||array|Return|
|&emsp;&emsp;poLookupKey||string||
|&emsp;&emsp;rdoId||string||
|&emsp;&emsp;returnTrackingId||string||
|&emsp;&emsp;trackingId||string||
|&emsp;&emsp;sku||string||
|&emsp;&emsp;rmaNumber||string||
|&emsp;&emsp;soId||string||
|&emsp;&emsp;poId||string||
|&emsp;&emsp;lineItemDetailedStatus||string||
|&emsp;&emsp;rsoId||string||
|&emsp;&emsp;channel||string||
|&emsp;&emsp;currentRefundStatus||string||
|&emsp;&emsp;labelGeneratedTs||string||
|&emsp;&emsp;refundDt||string||


**Response Example**:
```javascript
{
	"code": 0,
	"msg": "",
	"data": [
		{
			"poLookupKey": "",
			"rdoId": "",
			"returnTrackingId": "",
			"trackingId": "",
			"sku": "",
			"rmaNumber": "",
			"soId": "",
			"poId": "",
			"lineItemDetailedStatus": "",
			"rsoId": "",
			"channel": "",
			"currentRefundStatus": "",
			"labelGeneratedTs": "",
			"refundDt": ""
		}
	]
}
```