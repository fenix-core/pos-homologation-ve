
�i
base_data_type.protodata"
Empty"�
KeyValueSelection!
selection_id (RselectionId%
selection_uuid (	RselectionUuid&
values (2.data.KeyValueRvalues"?
KeyValue
key (	Rkey!
value (2.data.ValueRvalue"�
Value
	int_value (RintValue

long_value (R	longValue#
boolean_value (RbooleanValue!
string_value (	RstringValue2
decimal_value (2.data.DecimalRdecimalValue4

value_type (2.data.Value.ValueTypeR	valueType"U
	ValueType
UNKNOWN 
INTEGER
DECIMAL
BOOLEAN

STRING
DATE"D
Decimal#
decimal_value (	RdecimalValue
scale (Rscale"�
Criteria

table_name (	R	tableName
query (	Rquery!
where_clause (	RwhereClause&
order_by_clause (	RorderByClause%
reference_uuid (	RreferenceUuid/

conditions (2.data.ConditionR
conditions#
values (2.data.ValueRvalues=
order_by_column (2.data.OrderByPropertyRorderByColumn
limit	 (Rlimit"b
OrderByProperty
column_name (	R
columnName.

order_type (2.data.OrderTypeR	orderType"�
	Condition
column_name (	R
columnName!
value (2.data.ValueRvalue&
value_to (2.data.ValueRvalueTo#
values (2.data.ValueRvalues*
operator (2.data.OperatorRoperator/

conditions (2.data.ConditionR
conditions"�
RecordReferenceInfo
uuid (	Ruuid
window_uuid (	R
windowUuid
tab_uuid (	RtabUuid

table_name (	R	tableName!
where_clause (	RwhereClause!
record_count (RrecordCount
column_name (	R
columnName!
display_name (	RdisplayName!
value	 (2.data.ValueRvalue"\
DocumentStatus
value (	Rvalue
name (	Rname 
description (	Rdescription"\
DocumentAction
value (	Rvalue
name (	Rname 
description (	Rdescription"�
Entity
id (Rid
uuid (	Ruuid

table_name (	R	tableName0
values (2.data.Entity.ValuesEntryRvaluesF
ValuesEntry
key (	Rkey!
value (2.data.ValueRvalue:8"�
ProcesInstanceParameter
id (Rid
uuid (	Ruuid
name (	Rname
column_name (	R
columnName!
value (2.data.ValueRvalue&
value_to (2.data.ValueRvalueTo"�

ProcessLog
uuid (	Ruuid
name (	Rname 
description (	Rdescription#
instance_uuid (	RinstanceUuid
is_error (RisError
summary (	Rsummary*
result_table_name (	RresultTableName#
is_processing (RisProcessing
last_run	 (RlastRun(
logs
 (2.data.ProcessInfoLogRlogs@

parameters (2 .data.ProcessLog.ParametersEntryR
parameters*
output (2.data.ReportOutputRoutput[
process_intance_parameters (2.data.ProcesInstanceParameterRprocessIntanceParametersJ
ParametersEntry
key (	Rkey!
value (2.data.ValueRvalue:8"?
ProcessInfoLog
	record_id (RrecordId
log (	Rlog"�
Translation
language (	Rlanguage
uuid (	Ruuid5
values (2.data.Translation.ValuesEntryRvaluesF
ValuesEntry
key (	Rkey!
value (2.data.ValueRvalue:8"�
PrintFormat
uuid (	Ruuid
name (	Rname 
description (	Rdescription

table_name (	R	tableName

is_default (R	isDefault(
report_view_uuid (	RreportViewUuid"u

ReportView
uuid (	Ruuid
name (	Rname 
description (	Rdescription

table_name (	R	tableName"J

DrillTable

table_name (	R	tableName

print_name (	R	printName"�
ReportOutput
uuid (	Ruuid
name (	Rname 
description (	Rdescription
	file_name (	RfileName
output (	Routput
	mime_type (	RmimeType
	data_cols (RdataCols
	data_rows (RdataRows
header_name	 (	R
headerName
footer_name
 (	R
footerName*
print_format_uuid (	RprintFormatUuid(
report_view_uuid (	RreportViewUuid

table_name (	R	tableName#
output_stream (RoutputStream
report_type (	R
reportType**
	OrderType
	ASCENDING 

DESCENDING*�
Operator
VOID 	
EQUAL
	NOT_EQUAL
LIKE
NOT_LIKE
GREATER
GREATER_EQUAL
LESS

LESS_EQUAL
BETWEEN	
NOT_BETWEEN

NOT_NULL
NULL
IN

NOT_INB/
org.spin.backend.grpc.commonBADempiereBasePJ�H
 �
�	
 �	***********************************************************************************
 Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
 Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 This program is free software: you can redistribute it and/or modify             *
 it under the terms of the GNU General Public License as published by             *
 the Free Software Foundation, either version 2 of the License, or                *
 (at your option) any later version.                                              *
 This program is distributed in the hope that it will be useful,                  *
 but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 GNU General Public License for more details.                                     *
 You should have received a copy of the GNU General Public License                *
 along with this program. If not, see <https://www.gnu.org/licenses/>.            *
**********************************************************************************

 "
	

 "

 5
	
 5

 .
	
 .

 

    Empty message



 
!
  	Entities Selections





 

 

 

 

"





 !

%





 

#$
!
# & Key and Value pairs



#

 $

 $

 $

 $

%

%

%

%
%
) 8 Define value for object



)

 *

 *

 *

 *

+

+

+

+

,

,

,

,

- 

-

-

-

."

.

.

. !

 /6	

 /

  0

  0

  0

 1

 1

 1

 2

 2

 2

 3

 3

 3

 4

 4

 4

 5

 5

 5

7!

7

7

7 
"
; > Define numeric types



;

 <!

 <

 <

 < 

=

=

=

=
&
A K Query for Request Object



A

 B

 B

 B

 B

C

C

C

C

D 

D

D

D

E#

E

E

E!"

F"

F

F

F !

G*

G

G

G%

G()

H"

H

H

H

H !

I5

I

I 

I!0

I34

J

J

J

J


 M P


 M

  N

  N

  N

 O

 O

 O

S W Order By Property



S

 T

 T

 T

 T

V!	Operators


V

V

V 


Y i


Y

 Z

 Z

 Z

[

[

[

\

\

\

]

]

]

^

^

^

_

_

_

`

`

`

a

a

a

b

b

b

	c

	c

	c


d


d


d

e

e

e

f

f

f

g

g


g

h

h

h
&
l t Condition for query data



l

 m

 m

 m

 m

n

n

n

n

o

o

o

o

p"

p

p

p

p !

r	Operators


r

r

r

s*

s

s

s%

s()

w � Zoom Information



w

 x

 x

 x

 x

y

y

y

y

z

z

z

z

{

{

{

{

| 

|

|

|

}

}

}

}

~

~

~

~

 







�

�

�

�

	� � Document Item


	�

	 �

	 �

	 �

	 �

	�

	�

	�

	�

	�

	�

	�

	�


� � Document Item



�


 �


 �


 �


 �


�


�


�


�


�


�


�


�
+
� � Value Object from ADempiere


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�&

�

�!

�$%
8
� �*	Response with log and values from server


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

� �

�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�!

�

�

� 

�

�

�

�

�

�

�

�

�%

�

� 

�#$

�

�

�

�

�

�

�

�

	�*

	�

	�

	� $

	�')


�+


�


�%


�(*

�!

�

�

� 

�I

�

�(

�)C

�FH
*
� �	BusinessProcess Log result


�

 �

 �

 �

 �

�

�

�

�
!
� � Translations Item


�

 �

 �

 �

 �

�

�

�

�

�&

�

�!

�$%

� � Print Format


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�$

�

�

�"#

� � Report View


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�

�

�

�

� � Drill Table


�

 �

 �

 �

 �

�

�

�

�
t
� �f	Used for get output from report / BusinessProcess like PDF, HTML another result for show to end user


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

	� 

	�

	�

	�


�&


�


� 


�#%

�%

�

�

�"$

�

�

�

�

�!

�

�

� 

� 	Output Type


�

�

�bproto3
�[
core_functionality.protodatabase_data_type.proto"�
Country
uuid (	Ruuid
id (Rid!
country_code (	RcountryCode
name (	Rname 
description (	Rdescription

has_region (R	hasRegion
region_name (	R
regionName)
display_sequence (	RdisplaySequence7
is_address_lines_reverse	 (RisAddressLinesReverse)
capture_sequence
 (	RcaptureSequence4
display_sequence_local (	RdisplaySequenceLocalB
is_address_lines_local_reverse (RisAddressLinesLocalReverse+
expression_postal (	RexpressionPostal$
has_postal_add (RhasPostalAdd)
expression_phone (	RexpressionPhone

media_size (	R	mediaSize;
expression_bank_routing_no (	RexpressionBankRoutingNo;
expression_bank_account_no (	RexpressionBankAccountNo
language (	Rlanguage6
allow_cities_out_of_list (RallowCitiesOutOfList,
is_postcode_lookup (RisPostcodeLookup*
currency (2.data.CurrencyRcurrency"�
Currency
uuid (	Ruuid
id (Rid
iso_code (	RisoCode

cur_symbol (	R	curSymbol 
description (	Rdescription-
standard_precision (RstandardPrecision+
costing_precision (RcostingPrecision"e
	Warehouse
id (Rid
uuid (	Ruuid
name (	Rname 
description (	Rdescription"�
UnitOfMeasure
uuid (	Ruuid
id (Rid
code (	Rcode
symbol (	Rsymbol
name (	Rname 
description (	Rdescription-
standard_precision (RstandardPrecision+
costing_precision (RcostingPrecision"�
ProductConversion
id (Rid
uuid (	Ruuid%
uom (2.data.UnitOfMeasureRuom4
product_uom (2.data.UnitOfMeasureR
productUom2
multiply_rate (2.data.DecimalRmultiplyRate.
divide_rate (2.data.DecimalR
divideRate"b
Charge
id (Rid
uuid (	Ruuid
name (	Rname 
description (	Rdescription"�
DocumentType
uuid (	Ruuid
id (Rid
name (	Rname

print_name (	R	printName 
description (	Rdescription"o
SalesRepresentative
id (Rid
uuid (	Ruuid
name (	Rname 
description (	Rdescription"�
Product
uuid (	Ruuid
id (Rid
value (	Rvalue
name (	Rname
help (	Rhelp#
document_note (	RdocumentNote
uom_name (	RuomName!
product_type (	RproductType

is_stocked	 (R	isStocked 
is_drop_ship
 (R
isDropShip!
is_purchased (RisPurchased
is_sold (RisSold
	image_url (	RimageUrl2
product_category_name (	RproductCategoryName,
product_group_name (	RproductGroupName,
product_class_name (	RproductClassName>
product_classification_name (	RproductClassificationName%
weight (2.data.DecimalRweight%
volume (2.data.DecimalRvolume
upc (	Rupc
sku (	Rsku
shelf_width (R
shelfWidth0
shelf_height (2.data.DecimalRshelfHeight
shelf_depth (R
shelfDepth$
units_per_pack (RunitsPerPack7
units_per_pallet (2.data.DecimalRunitsPerPallet%
guarantee_days (RguaranteeDays'
description_url (	RdescriptionUrl

version_no (	R	versionNo!
tax_category (	RtaxCategory 
description (	Rdescription"�
TaxRate
uuid (	Ruuid
id (Rid
name (	Rname 
description (	Rdescription#
tax_indicator (	RtaxIndicator!
rate (2.data.DecimalRrate"�
	PriceList
uuid (	Ruuid
id (Rid
name (	Rname 
description (	Rdescription*
currency (2.data.CurrencyRcurrency

is_default (R	isDefault&
is_tax_included (RisTaxIncluded3
is_enforce_price_limit (RisEnforcePriceLimit 
is_net_price	 (R
isNetPrice'
price_precision
 (RpricePrecisionB<
org.spin.backend.grpc.commonBADempiereCoreFunctionalityPJ�=
 �
�	
 �	***********************************************************************************
 Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
 Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 This program is free software: you can redistribute it and/or modify             *
 it under the terms of the GNU General Public License as published by             *
 the Free Software Foundation, either version 2 of the License, or                *
 (at your option) any later version.                                              *
 This program is distributed in the hope that it will be useful,                  *
 but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 GNU General Public License for more details.                                     *
 You should have received a copy of the GNU General Public License                *
 along with this program. If not, see <https://www.gnu.org/licenses/>.            *
**********************************************************************************

 "
	

 "

 5
	
 5

 ;
	
 ;
	
  

 

  1	 Country



 

  

  

  

  

 

 

 

 

  

 

 

 

 

 

 

 

 

 

 

 

  

  

  

  

 !

 !

 !

 !

 "$

 "

 "

 ""#

 #*

 #

 #%

 #()

 	$%

 	$

 	$

 	$"$

 
%+

 
%

 
%%

 
%(*

 &1

 &

 &+

 &.0

 '&

 '

 ' 

 '#%

 (!

 (

 (

 ( 

 )%

 )

 )

 )"$

 *

 *

 *

 *

 +/

 +

 +)

 +,.

 ,/

 ,

 ,)

 ,,.

 -

 -

 -

 -

 .+

 .

 .%

 .(*

 /%

 /

 /

 /"$

 0

 0

 0

 0

5 = Currency info



5

 6

 6

 6

 6

7

7

7

7

8

8

8

8

9

9

9

9

:

:

:

:

;%

;

; 

;#$

<$

<

<

<"#

@ E Warehouse



@

 A

 A

 A

 A

B

B

B

B

C

C

C

C

D

D

D

D

I R Unit of Measure



I

 J

 J

 J

 J

K

K

K

K

L

L

L

L

M

M

M

M

N

N

N

N

O

O

O

O

P%

P

P 

P#$

Q$

Q

Q

Q"#
$
U \ Product UOM Conversion



U

 V

 V

 V

 V

W

W

W

W

X

X

X

X

Y&

Y

Y!

Y$%

Z"

Z

Z

Z !

[ 

[

[

[

` e Charge definition



`

 a

 a

 a

 a

b

b

b

b

c

c

c

c

d

d

d

d
&
i o Document Type definition



i

 j

 j

 j

 j

k

k

k

k

l

l

l

l

m

m

m

m

n

n

n

n
-
r w! Sales Representative definition



r

 s

 s

 s

 s

t

t

t

t

u

u

u

u

v

v

v

v
!
z � Product Definition



z

 {

 {

 {

 {

|

|

|

|

}

}

}

}

~

~

~

~









�!

�

�

� 

�

�

�

�

� 

�

�

�

�

�

�

�

	�

	�

	�

	�


�


�


�


�

�

�

�

�

�

�

�

�

�*

�

�$

�')

�'

�

�!

�$&

�'

�

�!

�$&

�0

�

�*

�-/

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�"

�

�

�!

�

�

�

�

�"

�

�

�!

�&

�

� 

�#%

�"

�

�

�!

�$

�

�

�!#

�

�

�

�

�!

�

�

� 

� 

�

�

�

	� �
 Tax Rate


	�

	 �

	 �

	 �

	 �

	�

	�

	�

	�

	�

	�

	�

	�

	�

	�

	�

	�

	�!

	�

	�

	� 

	�

	�

	�

	�


� �	Price List



�


 �


 �


 �


 �


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�


�!


�


�


� 


�(


�


�#


�&'


�


�


�


�


	�#


	�


	�


	� "bproto3
�x
google/api/http.proto
google.api"y
Http*
rules (2.google.api.HttpRuleRrulesE
fully_decode_reserved_expansion (RfullyDecodeReservedExpansion"�
HttpRule
selector (	Rselector
get (	H Rget
put (	H Rput
post (	H Rpost
delete (	H Rdelete
patch (	H Rpatch7
custom (2.google.api.CustomHttpPatternH Rcustom
body (	Rbody#
response_body (	RresponseBodyE
additional_bindings (2.google.api.HttpRuleRadditionalBindingsB	
pattern";
CustomHttpPattern
kind (	Rkind
path (	RpathBj
com.google.apiB	HttpProtoPZAgoogle.golang.org/genproto/googleapis/api/annotations;annotations��GAPIJ�s
 �
�
 2� Copyright 2023 Google LLC

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.


 

 
	
 

 X
	
 X

 "
	

 "

 *
	
 *

 '
	
 '

 "
	
$ "
�
  )� Defines the HTTP configuration for an API service. It contains a list of
 [HttpRule][google.api.HttpRule], each specifying the mapping of an RPC method
 to one or more HTTP REST API methods.



 
�
   � A list of HTTP configuration rules that apply to individual API methods.

 **NOTE:** All service configuration rules follow "last one wins" order.


   


   

   

   
�
 (+� When set to true, URL path parameters will be fully URI-decoded except in
 cases of single segment matches in reserved expansion, where "%2F" will be
 left encoded.

 The default behavior is to not decode RFC 6570 reserved characters in multi
 segment matches.


 (

 (&

 ()*
�S
� ��S # gRPC Transcoding

 gRPC Transcoding is a feature for mapping between a gRPC method and one or
 more HTTP REST endpoints. It allows developers to build a single API service
 that supports both gRPC APIs and REST APIs. Many systems, including [Google
 APIs](https://github.com/googleapis/googleapis),
 [Cloud Endpoints](https://cloud.google.com/endpoints), [gRPC
 Gateway](https://github.com/grpc-ecosystem/grpc-gateway),
 and [Envoy](https://github.com/envoyproxy/envoy) proxy support this feature
 and use it for large scale production services.

 `HttpRule` defines the schema of the gRPC/REST mapping. The mapping specifies
 how different portions of the gRPC request message are mapped to the URL
 path, URL query parameters, and HTTP request body. It also controls how the
 gRPC response message is mapped to the HTTP response body. `HttpRule` is
 typically specified as an `google.api.http` annotation on the gRPC method.

 Each mapping specifies a URL path template and an HTTP method. The path
 template may refer to one or more fields in the gRPC request message, as long
 as each field is a non-repeated field with a primitive (non-message) type.
 The path template controls how fields of the request message are mapped to
 the URL path.

 Example:

     service Messaging {
       rpc GetMessage(GetMessageRequest) returns (Message) {
         option (google.api.http) = {
             get: "/v1/{name=messages/*}"
         };
       }
     }
     message GetMessageRequest {
       string name = 1; // Mapped to URL path.
     }
     message Message {
       string text = 1; // The resource content.
     }

 This enables an HTTP REST to gRPC mapping as below:

 HTTP | gRPC
 -----|-----
 `GET /v1/messages/123456`  | `GetMessage(name: "messages/123456")`

 Any fields in the request message which are not bound by the path template
 automatically become HTTP query parameters if there is no HTTP request body.
 For example:

     service Messaging {
       rpc GetMessage(GetMessageRequest) returns (Message) {
         option (google.api.http) = {
             get:"/v1/messages/{message_id}"
         };
       }
     }
     message GetMessageRequest {
       message SubMessage {
         string subfield = 1;
       }
       string message_id = 1; // Mapped to URL path.
       int64 revision = 2;    // Mapped to URL query parameter `revision`.
       SubMessage sub = 3;    // Mapped to URL query parameter `sub.subfield`.
     }

 This enables a HTTP JSON to RPC mapping as below:

 HTTP | gRPC
 -----|-----
 `GET /v1/messages/123456?revision=2&sub.subfield=foo` |
 `GetMessage(message_id: "123456" revision: 2 sub: SubMessage(subfield:
 "foo"))`

 Note that fields which are mapped to URL query parameters must have a
 primitive type or a repeated primitive type or a non-repeated message type.
 In the case of a repeated type, the parameter can be repeated in the URL
 as `...?param=A&param=B`. In the case of a message type, each field of the
 message is mapped to a separate parameter, such as
 `...?foo.a=A&foo.b=B&foo.c=C`.

 For HTTP methods that allow a request body, the `body` field
 specifies the mapping. Consider a REST update method on the
 message resource collection:

     service Messaging {
       rpc UpdateMessage(UpdateMessageRequest) returns (Message) {
         option (google.api.http) = {
           patch: "/v1/messages/{message_id}"
           body: "message"
         };
       }
     }
     message UpdateMessageRequest {
       string message_id = 1; // mapped to the URL
       Message message = 2;   // mapped to the body
     }

 The following HTTP JSON to RPC mapping is enabled, where the
 representation of the JSON in the request body is determined by
 protos JSON encoding:

 HTTP | gRPC
 -----|-----
 `PATCH /v1/messages/123456 { "text": "Hi!" }` | `UpdateMessage(message_id:
 "123456" message { text: "Hi!" })`

 The special name `*` can be used in the body mapping to define that
 every field not bound by the path template should be mapped to the
 request body.  This enables the following alternative definition of
 the update method:

     service Messaging {
       rpc UpdateMessage(Message) returns (Message) {
         option (google.api.http) = {
           patch: "/v1/messages/{message_id}"
           body: "*"
         };
       }
     }
     message Message {
       string message_id = 1;
       string text = 2;
     }


 The following HTTP JSON to RPC mapping is enabled:

 HTTP | gRPC
 -----|-----
 `PATCH /v1/messages/123456 { "text": "Hi!" }` | `UpdateMessage(message_id:
 "123456" text: "Hi!")`

 Note that when using `*` in the body mapping, it is not possible to
 have HTTP parameters, as all fields not bound by the path end in
 the body. This makes this option more rarely used in practice when
 defining REST APIs. The common usage of `*` is in custom methods
 which don't use the URL at all for transferring data.

 It is possible to define multiple HTTP methods for one RPC by using
 the `additional_bindings` option. Example:

     service Messaging {
       rpc GetMessage(GetMessageRequest) returns (Message) {
         option (google.api.http) = {
           get: "/v1/messages/{message_id}"
           additional_bindings {
             get: "/v1/users/{user_id}/messages/{message_id}"
           }
         };
       }
     }
     message GetMessageRequest {
       string message_id = 1;
       string user_id = 2;
     }

 This enables the following two alternative HTTP JSON to RPC mappings:

 HTTP | gRPC
 -----|-----
 `GET /v1/messages/123456` | `GetMessage(message_id: "123456")`
 `GET /v1/users/me/messages/123456` | `GetMessage(user_id: "me" message_id:
 "123456")`

 ## Rules for HTTP mapping

 1. Leaf request fields (recursive expansion nested messages in the request
    message) are classified into three categories:
    - Fields referred by the path template. They are passed via the URL path.
    - Fields referred by the [HttpRule.body][google.api.HttpRule.body]. They
    are passed via the HTTP
      request body.
    - All other fields are passed via the URL query parameters, and the
      parameter name is the field path in the request message. A repeated
      field can be represented as multiple query parameters under the same
      name.
  2. If [HttpRule.body][google.api.HttpRule.body] is "*", there is no URL
  query parameter, all fields
     are passed via URL path and HTTP request body.
  3. If [HttpRule.body][google.api.HttpRule.body] is omitted, there is no HTTP
  request body, all
     fields are passed via URL path and URL query parameters.

 ### Path template syntax

     Template = "/" Segments [ Verb ] ;
     Segments = Segment { "/" Segment } ;
     Segment  = "*" | "**" | LITERAL | Variable ;
     Variable = "{" FieldPath [ "=" Segments ] "}" ;
     FieldPath = IDENT { "." IDENT } ;
     Verb     = ":" LITERAL ;

 The syntax `*` matches a single URL path segment. The syntax `**` matches
 zero or more URL path segments, which must be the last part of the URL path
 except the `Verb`.

 The syntax `Variable` matches part of the URL path as specified by its
 template. A variable template must not contain other variables. If a variable
 matches a single path segment, its template may be omitted, e.g. `{var}`
 is equivalent to `{var=*}`.

 The syntax `LITERAL` matches literal text in the URL path. If the `LITERAL`
 contains any reserved character, such characters should be percent-encoded
 before the matching.

 If a variable contains exactly one path segment, such as `"{var}"` or
 `"{var=*}"`, when such a variable is expanded into a URL path on the client
 side, all characters except `[-_.~0-9a-zA-Z]` are percent-encoded. The
 server side does the reverse decoding. Such variables show up in the
 [Discovery
 Document](https://developers.google.com/discovery/v1/reference/apis) as
 `{var}`.

 If a variable contains multiple path segments, such as `"{var=foo/*}"`
 or `"{var=**}"`, when such a variable is expanded into a URL path on the
 client side, all characters except `[-_.~/0-9a-zA-Z]` are percent-encoded.
 The server side does the reverse decoding, except "%2F" and "%2f" are left
 unchanged. Such variables show up in the
 [Discovery
 Document](https://developers.google.com/discovery/v1/reference/apis) as
 `{+var}`.

 ## Using gRPC API Service Configuration

 gRPC API Service Configuration (service config) is a configuration language
 for configuring a gRPC service to become a user-facing product. The
 service config is simply the YAML representation of the `google.api.Service`
 proto message.

 As an alternative to annotating your proto file, you can configure gRPC
 transcoding in your service config YAML files. You do this by specifying a
 `HttpRule` that maps the gRPC method to a REST endpoint, achieving the same
 effect as the proto annotation. This can be particularly useful if you
 have a proto that is reused in multiple services. Note that any transcoding
 specified in the service config will override any matching transcoding
 configuration in the proto.

 Example:

     http:
       rules:
         # Selects a gRPC method and applies HttpRule to it.
         - selector: example.v1.Messaging.GetMessage
           get: /v1/messages/{message_id}/{sub.subfield}

 ## Special notes

 When gRPC Transcoding is used to map a gRPC to JSON REST endpoints, the
 proto to JSON conversion must follow the [proto3
 specification](https://developers.google.com/protocol-buffers/docs/proto3#json).

 While the single segment variable follows the semantics of
 [RFC 6570](https://tools.ietf.org/html/rfc6570) Section 3.2.2 Simple String
 Expansion, the multi segment variable **does not** follow RFC 6570 Section
 3.2.3 Reserved Expansion. The reason is that the Reserved Expansion
 does not expand special characters like `?` and `#`, which would lead
 to invalid URLs. As the result, gRPC Transcoding uses a custom encoding
 for multi segment variables.

 The path variables **must not** refer to any repeated or mapped field,
 because client libraries are not capable of handling such variable expansion.

 The path variables **must not** capture the leading "/" character. The reason
 is that the most common use case "{var}" does not capture the leading "/"
 character. For consistency, all path variables must share the same behavior.

 Repeated message fields must not be mapped to URL query parameters, because
 no client library can support such complicated mapping.

 If an API needs to use a JSON array for request or response body, it can map
 the request or response body to a repeated field. However, some gRPC
 Transcoding implementations may not support this feature.


�
�
 �� Selects a method to which this rule applies.

 Refer to [selector][google.api.DocumentationRule.selector] for syntax
 details.


 �

 �	

 �
�
 ��� Determines the URL pattern is matched by this rules. This pattern can be
 used with any of the {get|put|post|delete|patch} methods. A custom method
 can be defined using the 'custom' field.


 �
\
�N Maps to HTTP GET. Used for listing and getting information about
 resources.


�


�

�
@
�2 Maps to HTTP PUT. Used for replacing a resource.


�


�

�
X
�J Maps to HTTP POST. Used for creating a resource or performing an action.


�


�

�
B
�4 Maps to HTTP DELETE. Used for deleting a resource.


�


�

�
A
�3 Maps to HTTP PATCH. Used for updating a resource.


�


�

�
�
�!� The custom pattern is used for specifying an HTTP method that is not
 included in the `pattern` field, such as HEAD, or "*" to leave the
 HTTP method unspecified for this rule. The wild-card rule is useful
 for services that provide content to Web (HTML) clients.


�

�

� 
�
�� The name of the request field whose value is mapped to the HTTP request
 body, or `*` for mapping all request fields not captured by the path
 pattern to the HTTP body, or omitted for not having any HTTP request body.

 NOTE: the referred field must be present at the top-level of the request
 message type.


�

�	

�
�
�� Optional. The name of the response field whose value is mapped to the HTTP
 response body. When omitted, the entire response message will be used
 as the HTTP response body.

 NOTE: The referred field must be present at the top-level of the response
 message type.


�

�	

�
�
	�-� Additional HTTP bindings for the selector. Nested bindings must
 not contain an `additional_bindings` field themselves (that is,
 the nesting may only be one level deep).


	�


	�

	�'

	�*,
G
� �9 A custom pattern is used for defining custom HTTP verb.


�
2
 �$ The name of this custom HTTP verb.


 �

 �	

 �
5
�' The path matched by this custom verb.


�

�	

�bproto3
��
 google/protobuf/descriptor.protogoogle.protobuf"M
FileDescriptorSet8
file (2$.google.protobuf.FileDescriptorProtoRfile"�
FileDescriptorProto
name (	Rname
package (	Rpackage

dependency (	R
dependency+
public_dependency
 (RpublicDependency'
weak_dependency (RweakDependencyC
message_type (2 .google.protobuf.DescriptorProtoRmessageTypeA
	enum_type (2$.google.protobuf.EnumDescriptorProtoRenumTypeA
service (2'.google.protobuf.ServiceDescriptorProtoRserviceC
	extension (2%.google.protobuf.FieldDescriptorProtoR	extension6
options (2.google.protobuf.FileOptionsRoptionsI
source_code_info	 (2.google.protobuf.SourceCodeInfoRsourceCodeInfo
syntax (	Rsyntax2
edition (2.google.protobuf.EditionRedition"�
DescriptorProto
name (	Rname;
field (2%.google.protobuf.FieldDescriptorProtoRfieldC
	extension (2%.google.protobuf.FieldDescriptorProtoR	extensionA
nested_type (2 .google.protobuf.DescriptorProtoR
nestedTypeA
	enum_type (2$.google.protobuf.EnumDescriptorProtoRenumTypeX
extension_range (2/.google.protobuf.DescriptorProto.ExtensionRangeRextensionRangeD

oneof_decl (2%.google.protobuf.OneofDescriptorProtoR	oneofDecl9
options (2.google.protobuf.MessageOptionsRoptionsU
reserved_range	 (2..google.protobuf.DescriptorProto.ReservedRangeRreservedRange#
reserved_name
 (	RreservedNamez
ExtensionRange
start (Rstart
end (Rend@
options (2&.google.protobuf.ExtensionRangeOptionsRoptions7
ReservedRange
start (Rstart
end (Rend"�
ExtensionRangeOptionsX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOptionY
declaration (22.google.protobuf.ExtensionRangeOptions.DeclarationB�Rdeclaration7
features2 (2.google.protobuf.FeatureSetRfeaturesh
verification (28.google.protobuf.ExtensionRangeOptions.VerificationState:
UNVERIFIEDRverification�
Declaration
number (Rnumber
	full_name (	RfullName
type (	Rtype
reserved (Rreserved
repeated (RrepeatedJ"4
VerificationState
DECLARATION 

UNVERIFIED*	�����"�
FieldDescriptorProto
name (	Rname
number (RnumberA
label (2+.google.protobuf.FieldDescriptorProto.LabelRlabel>
type (2*.google.protobuf.FieldDescriptorProto.TypeRtype
	type_name (	RtypeName
extendee (	Rextendee#
default_value (	RdefaultValue
oneof_index	 (R
oneofIndex
	json_name
 (	RjsonName7
options (2.google.protobuf.FieldOptionsRoptions'
proto3_optional (Rproto3Optional"�
Type
TYPE_DOUBLE

TYPE_FLOAT

TYPE_INT64
TYPE_UINT64

TYPE_INT32
TYPE_FIXED64
TYPE_FIXED32
	TYPE_BOOL
TYPE_STRING	

TYPE_GROUP

TYPE_MESSAGE

TYPE_BYTES
TYPE_UINT32
	TYPE_ENUM
TYPE_SFIXED32
TYPE_SFIXED64
TYPE_SINT32
TYPE_SINT64"C
Label
LABEL_OPTIONAL
LABEL_REPEATED
LABEL_REQUIRED"c
OneofDescriptorProto
name (	Rname7
options (2.google.protobuf.OneofOptionsRoptions"�
EnumDescriptorProto
name (	Rname?
value (2).google.protobuf.EnumValueDescriptorProtoRvalue6
options (2.google.protobuf.EnumOptionsRoptions]
reserved_range (26.google.protobuf.EnumDescriptorProto.EnumReservedRangeRreservedRange#
reserved_name (	RreservedName;
EnumReservedRange
start (Rstart
end (Rend"�
EnumValueDescriptorProto
name (	Rname
number (Rnumber;
options (2!.google.protobuf.EnumValueOptionsRoptions"�
ServiceDescriptorProto
name (	Rname>
method (2&.google.protobuf.MethodDescriptorProtoRmethod9
options (2.google.protobuf.ServiceOptionsRoptions"�
MethodDescriptorProto
name (	Rname

input_type (	R	inputType
output_type (	R
outputType8
options (2.google.protobuf.MethodOptionsRoptions0
client_streaming (:falseRclientStreaming0
server_streaming (:falseRserverStreaming"�	
FileOptions!
java_package (	RjavaPackage0
java_outer_classname (	RjavaOuterClassname5
java_multiple_files
 (:falseRjavaMultipleFilesD
java_generate_equals_and_hash (BRjavaGenerateEqualsAndHash:
java_string_check_utf8 (:falseRjavaStringCheckUtf8S
optimize_for	 (2).google.protobuf.FileOptions.OptimizeMode:SPEEDRoptimizeFor

go_package (	R	goPackage5
cc_generic_services (:falseRccGenericServices9
java_generic_services (:falseRjavaGenericServices5
py_generic_services (:falseRpyGenericServices7
php_generic_services* (:falseRphpGenericServices%

deprecated (:falseR
deprecated.
cc_enable_arenas (:trueRccEnableArenas*
objc_class_prefix$ (	RobjcClassPrefix)
csharp_namespace% (	RcsharpNamespace!
swift_prefix' (	RswiftPrefix(
php_class_prefix( (	RphpClassPrefix#
php_namespace) (	RphpNamespace4
php_metadata_namespace, (	RphpMetadataNamespace!
ruby_package- (	RrubyPackage7
features2 (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption":
OptimizeMode	
SPEED
	CODE_SIZE
LITE_RUNTIME*	�����J&'"�
MessageOptions<
message_set_wire_format (:falseRmessageSetWireFormatL
no_standard_descriptor_accessor (:falseRnoStandardDescriptorAccessor%

deprecated (:falseR
deprecated
	map_entry (RmapEntryV
&deprecated_legacy_json_field_conflicts (BR"deprecatedLegacyJsonFieldConflicts7
features (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption*	�����JJJJ	J	
"�

FieldOptionsA
ctype (2#.google.protobuf.FieldOptions.CType:STRINGRctype
packed (RpackedG
jstype (2$.google.protobuf.FieldOptions.JSType:	JS_NORMALRjstype
lazy (:falseRlazy.
unverified_lazy (:falseRunverifiedLazy%

deprecated (:falseR
deprecated
weak
 (:falseRweak(
debug_redact (:falseRdebugRedactK
	retention (2-.google.protobuf.FieldOptions.OptionRetentionR	retentionH
targets (2..google.protobuf.FieldOptions.OptionTargetTypeRtargetsW
edition_defaults (2,.google.protobuf.FieldOptions.EditionDefaultReditionDefaults7
features (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOptionZ
EditionDefault2
edition (2.google.protobuf.EditionRedition
value (	Rvalue"/
CType

STRING 
CORD
STRING_PIECE"5
JSType
	JS_NORMAL 
	JS_STRING
	JS_NUMBER"U
OptionRetention
RETENTION_UNKNOWN 
RETENTION_RUNTIME
RETENTION_SOURCE"�
OptionTargetType
TARGET_TYPE_UNKNOWN 
TARGET_TYPE_FILE
TARGET_TYPE_EXTENSION_RANGE
TARGET_TYPE_MESSAGE
TARGET_TYPE_FIELD
TARGET_TYPE_ONEOF
TARGET_TYPE_ENUM
TARGET_TYPE_ENUM_ENTRY
TARGET_TYPE_SERVICE
TARGET_TYPE_METHOD	*	�����JJ"�
OneofOptions7
features (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption*	�����"�
EnumOptions
allow_alias (R
allowAlias%

deprecated (:falseR
deprecatedV
&deprecated_legacy_json_field_conflicts (BR"deprecatedLegacyJsonFieldConflicts7
features (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption*	�����J"�
EnumValueOptions%

deprecated (:falseR
deprecated7
features (2.google.protobuf.FeatureSetRfeatures(
debug_redact (:falseRdebugRedactX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption*	�����"�
ServiceOptions7
features" (2.google.protobuf.FeatureSetRfeatures%

deprecated! (:falseR
deprecatedX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption*	�����"�
MethodOptions%

deprecated! (:falseR
deprecatedq
idempotency_level" (2/.google.protobuf.MethodOptions.IdempotencyLevel:IDEMPOTENCY_UNKNOWNRidempotencyLevel7
features# (2.google.protobuf.FeatureSetRfeaturesX
uninterpreted_option� (2$.google.protobuf.UninterpretedOptionRuninterpretedOption"P
IdempotencyLevel
IDEMPOTENCY_UNKNOWN 
NO_SIDE_EFFECTS

IDEMPOTENT*	�����"�
UninterpretedOptionA
name (2-.google.protobuf.UninterpretedOption.NamePartRname)
identifier_value (	RidentifierValue,
positive_int_value (RpositiveIntValue,
negative_int_value (RnegativeIntValue!
double_value (RdoubleValue!
string_value (RstringValue'
aggregate_value (	RaggregateValueJ
NamePart
	name_part (	RnamePart!
is_extension (RisExtension"�	

FeatureSet�
field_presence (2).google.protobuf.FeatureSet.FieldPresenceB9����EXPLICIT��IMPLICIT��EXPLICIT�RfieldPresencef
	enum_type (2$.google.protobuf.FeatureSet.EnumTypeB#����CLOSED��	OPEN�RenumType�
repeated_field_encoding (21.google.protobuf.FeatureSet.RepeatedFieldEncodingB'����EXPANDED��PACKED�RrepeatedFieldEncodingx
utf8_validation (2*.google.protobuf.FeatureSet.Utf8ValidationB#����	NONE��VERIFY�Rutf8Validationx
message_encoding (2+.google.protobuf.FeatureSet.MessageEncodingB ����LENGTH_PREFIXED�RmessageEncoding|
json_format (2&.google.protobuf.FeatureSet.JsonFormatB3�����LEGACY_BEST_EFFORT��
ALLOW�R
jsonFormat"\
FieldPresence
FIELD_PRESENCE_UNKNOWN 
EXPLICIT
IMPLICIT
LEGACY_REQUIRED"7
EnumType
ENUM_TYPE_UNKNOWN 
OPEN

CLOSED"V
RepeatedFieldEncoding#
REPEATED_FIELD_ENCODING_UNKNOWN 

PACKED
EXPANDED"C
Utf8Validation
UTF8_VALIDATION_UNKNOWN 
NONE

VERIFY"S
MessageEncoding
MESSAGE_ENCODING_UNKNOWN 
LENGTH_PREFIXED
	DELIMITED"H

JsonFormat
JSON_FORMAT_UNKNOWN 	
ALLOW
LEGACY_BEST_EFFORT*��*��*�N�NJ��"�
FeatureSetDefaultsX
defaults (2<.google.protobuf.FeatureSetDefaults.FeatureSetEditionDefaultRdefaultsA
minimum_edition (2.google.protobuf.EditionRminimumEditionA
maximum_edition (2.google.protobuf.EditionRmaximumEdition�
FeatureSetEditionDefault2
edition (2.google.protobuf.EditionRedition7
features (2.google.protobuf.FeatureSetRfeatures"�
SourceCodeInfoD
location (2(.google.protobuf.SourceCodeInfo.LocationRlocation�
Location
path (BRpath
span (BRspan)
leading_comments (	RleadingComments+
trailing_comments (	RtrailingComments:
leading_detached_comments (	RleadingDetachedComments"�
GeneratedCodeInfoM

annotation (2-.google.protobuf.GeneratedCodeInfo.AnnotationR
annotation�

Annotation
path (BRpath
source_file (	R
sourceFile
begin (Rbegin
end (RendR
semantic (26.google.protobuf.GeneratedCodeInfo.Annotation.SemanticRsemantic"(
Semantic
NONE 
SET	
ALIAS*�
Edition
EDITION_UNKNOWN 
EDITION_PROTO2�
EDITION_PROTO3�
EDITION_2023�
EDITION_1_TEST_ONLY
EDITION_2_TEST_ONLY
EDITION_99997_TEST_ONLY��
EDITION_99998_TEST_ONLY��
EDITION_99999_TEST_ONLY��B~
com.google.protobufBDescriptorProtosHZ-google.golang.org/protobuf/types/descriptorpb��GPB�Google.Protobuf.ReflectionJ��
& �	
�
& 2� Protocol Buffers - Google's data interchange format
 Copyright 2008 Google Inc.  All rights reserved.
 https://developers.google.com/protocol-buffers/

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are
 met:

     * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above
 copyright notice, this list of conditions and the following disclaimer
 in the documentation and/or other materials provided with the
 distribution.
     * Neither the name of Google Inc. nor the names of its
 contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
2� Author: kenton@google.com (Kenton Varda)
  Based on original Protocol Buffers design by
  Sanjay Ghemawat, Jeff Dean, and others.

 The messages in this file describe the definitions found in .proto files.
 A valid .proto file can be translated directly to a FileDescriptorProto
 without any other information (e.g. without reading its imports).


( 

* D
	
* D

+ ,
	
+ ,

, 1
	
, 1

- 7
	
%- 7

. !
	
$. !

/ 
	
/ 

3 

	3 t descriptor.proto must be optimized for speed because reflection-based
 algorithms don't work during bootstrapping.

j
 7 9^ The protocol compiler can output a FileDescriptorSet containing the .proto
 files it parses.



 7

  8(

  8


  8

  8#

  8&'
-
 < S! The full set of known editions.



 <
:
  >- A placeholder for an unknown edition value.


  >

  >
�
 D� Legacy syntax "editions".  These pre-date editions, but behave much like
 distinct editions.  These can't be used to specify the edition of proto
 files, but feature definitions must supply proto2/proto3 defaults for
 backwards compatibility.


 D

 D

 E

 E

 E
�
 J� Editions that have been released.  The specific values are arbitrary and
 should not be depended on, but they will always be time-ordered for easy
 comparison.


 J

 J
}
 Np Placeholder editions for testing feature resolution.  These should not be
 used or relyed on outside of tests.


 N

 N

 O

 O

 O

 P"

 P

 P!

 Q"

 Q

 Q!

 R"

 R

 R!
/
V x# Describes a complete .proto file.



V
9
 W", file name, relative to root of source tree


 W


 W

 W

 W
*
X" e.g. "foo", "foo.bar", etc.


X


X

X

X
4
[!' Names of files imported by this file.


[


[

[

[ 
Q
](D Indexes of the public imported files in the dependency list above.


]


]

]"

]%'
z
`&m Indexes of the weak imported files in the dependency list.
 For Google-internal migration only. Do not use.


`


`

` 

`#%
6
c,) All top-level definitions in this file.


c


c

c'

c*+

d-

d


d

d(

d+,

e.

e


e!

e")

e,-

f.

f


f

f )

f,-

	h#

	h


	h

	h

	h!"
�

n/� This field contains optional information about the original source code.
 You may safely remove this entire field without harming runtime
 functionality of the descriptors -- the information is needed only by
 development tools.



n



n


n*


n-.
�
t� The syntax of the proto file.
 The supported values are "proto2", "proto3", and "editions".

 If `edition` is present, this value must be "editions".


t


t

t

t
-
w   The edition of the proto file.


w


w

w

w
(
{ � Describes a message type.



{

 |

 |


 |

 |

 |

~*

~


~

~ %

~()

.






 )

,-

�+

�


�

�&

�)*

�-

�


�

�(

�+,

 ��

 �


  �" Inclusive.


  �

  �

  �

  �

 �" Exclusive.


 �

 �

 �

 �

 �/

 �

 �"

 �#*

 �-.

�.

�


�

�)

�,-

�/

�


�

� *

�-.

�&

�


�

�!

�$%
�
��� Range of reserved tag numbers. Reserved tag numbers may not be used by
 fields or extension ranges in the same message. Reserved ranges may
 not overlap.


�


 �" Inclusive.


 �

 �

 �

 �

�" Exclusive.


�

�

�

�

�,

�


�

�'

�*+
�
	�%u Reserved field names, which may not be used by fields in the same message.
 A given name may only be reserved once.


	�


	�

	�

	�"$

� �

�
O
 �:A The parser stores options it doesn't recognize here. See above.


 �


 �

 �3

 �69

 ��

 �

K
  �; The extension number declared within the extension range.


  �

  �

  �

  �
z
 �"j The fully-qualified name of the extension field. There must be a leading
 dot in front of the full name.


 �

 �

 �

 � !
�
 �� The fully-qualified type name of the extension field. Unlike
 Metadata.type, Declaration.type must have a leading dot for messages
 and enums.


 �

 �

 �

 �
�
 �� If true, indicates that the number is reserved in the extension range,
 and any extension field with the number will fail to compile. Set this
 when a declared extension field is deleted.


 �

 �

 �

 �
�
 �z If true, indicates that the extension must be defined as repeated.
 Otherwise the extension must be defined as optional.


 �

 �

 �

 �
$
 	�" removed is_repeated


 	 �

 	 �

 	 �
�
�F� For external users: DO NOT USE. We are in the process of open sourcing
 extension declaration and executing internal cleanups before it can be
 used externally.


�


�

�"

�%&

�'E

�(D
=
�$/ Any features defined in the specific edition.


�


�

�

�!#
@
 ��0 The verification state of the extension range.


 �
C
  �3 All the extensions of the range must be declared.


  �

  �

 �

 �

 �
�
�E~ The verification state of the range.
 TODO: flip the default to DECLARATION once all empty ranges
 are marked as UNVERIFIED.


�


�

�)

�,-

�.D

�9C
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �
3
� �% Describes a field within a message.


�

 ��

 �
S
  �C 0 is reserved for errors.
 Order is weird for historical reasons.


  �

  �

 �

 �

 �
w
 �g Not ZigZag encoded.  Negative numbers take 10 bytes.  Use TYPE_SINT64 if
 negative values are likely.


 �

 �

 �

 �

 �
w
 �g Not ZigZag encoded.  Negative numbers take 10 bytes.  Use TYPE_SINT32 if
 negative values are likely.


 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �
�
 	�� Tag-delimited aggregate.
 Group type is deprecated and not supported after google.protobuf. However, Proto3
 implementations should still be able to parse the group wire format and
 treat group fields as unknown fields.  In Editions, the group wire format
 can be enabled via the `message_encoding` feature.


 	�

 	�
-
 
�" Length-delimited aggregate.


 
�

 
�
#
 � New in version 2.


 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 �
'
 �" Uses ZigZag encoding.


 �

 �
'
 �" Uses ZigZag encoding.


 �

 �

��

�
*
 � 0 is reserved for errors


 �

 �

�

�

�
�
�� The required label is only allowed in google.protobuf.  In proto3 and Editions
 it's explicitly prohibited.  In Editions, the `field_presence` feature
 can be used to get this behavior.


�

�

 �

 �


 �

 �

 �

�

�


�

�

�

�

�


�

�

�
�
�� If type_name is set, this need not be set.  If both this and type_name
 are set, this must be one of TYPE_ENUM, TYPE_MESSAGE or TYPE_GROUP.


�


�

�

�
�
� � For message and enum types, this is the name of the type.  If the name
 starts with a '.', it is fully-qualified.  Otherwise, C++-like scoping
 rules are used to find the type (i.e. first the nested types within this
 message are searched, then within the parent, on up to the root
 namespace).


�


�

�

�
~
�p For extensions, this is the name of the type being extended.  It is
 resolved in the same manner as type_name.


�


�

�

�
�
�$� For numeric types, contains the original text representation of the value.
 For booleans, "true" or "false".
 For strings, contains the default text contents (not escaped in any way).
 For bytes, contains the C escaped value.  All bytes >= 128 are escaped.


�


�

�

�"#
�
�!v If set, gives the index of a oneof in the containing type's oneof_decl
 list.  This field is a member of that oneof.


�


�

�

� 
�
�!� JSON name of this field. The value is set by protocol compiler. If the
 user has set a "json_name" option on this field, that option's value
 will be used. Otherwise, it's deduced from the field's name by converting
 it to camelCase.


�


�

�

� 

	�$

	�


	�

	�

	�"#
�	

�%�	 If true, this is a proto3 "optional". When a proto3 field is optional, it
 tracks presence regardless of field type.

 When proto3_optional is true, this field must be belong to a oneof to
 signal to old proto3 clients that presence is tracked for this field. This
 oneof is known as a "synthetic" oneof, and this field must be its sole
 member (each proto3 optional field gets its own synthetic oneof). Synthetic
 oneofs exist in the descriptor only, and do not generate any API. Synthetic
 oneofs must be ordered after all "real" oneofs.

 For message fields, proto3_optional doesn't create any semantic change,
 since non-repeated message fields always track presence. However it still
 indicates the semantic detail of whether the user wrote "optional" or not.
 This can be useful for round-tripping the .proto file. For consistency we
 give message fields a synthetic oneof also, even though it is not required
 to track presence. This is especially important because the parser can't
 tell if a field is a message or an enum, so it must always create a
 synthetic oneof.

 Proto2 optional fields do not set this flag, because they already indicate
 optional with `LABEL_OPTIONAL`.



�



�


�


�"$
"
� � Describes a oneof.


�

 �

 �


 �

 �

 �

�$

�


�

�

�"#
'
� � Describes an enum type.


�

 �

 �


 �

 �

 �

�.

�


�#

�$)

�,-

�#

�


�

�

�!"
�
 ��� Range of reserved numeric values. Reserved values may not be used by
 entries in the same enum. Reserved ranges may not overlap.

 Note that this is distinct from DescriptorProto.ReservedRange in that it
 is inclusive such that it can appropriately represent the entire int32
 domain.


 �


  �" Inclusive.


  �

  �

  �

  �

 �" Inclusive.


 �

 �

 �

 �
�
�0� Range of reserved numeric values. Reserved numeric values may not be used
 by enum values in the same enum declaration. Reserved ranges may not
 overlap.


�


�

�+

�./
l
�$^ Reserved enum value names, which may not be reused. A given name may only
 be reserved once.


�


�

�

�"#
1
� �# Describes a value within an enum.


� 

 �

 �


 �

 �

 �

�

�


�

�

�

�(

�


�

�#

�&'
$
� � Describes a service.


�

 �

 �


 �

 �

 �

�,

�


� 

�!'

�*+

�&

�


�

�!

�$%
0
	� �" Describes a method of a service.


	�

	 �

	 �


	 �

	 �

	 �
�
	�!� Input and output type names.  These are resolved in the same way as
 FieldDescriptorProto.type_name, but must refer to a message type.


	�


	�

	�

	� 

	�"

	�


	�

	�

	� !

	�%

	�


	�

	� 

	�#$
E
	�77 Identifies if client streams multiple client messages


	�


	�

	� 

	�#$

	�%6

	�05
E
	�77 Identifies if server streams multiple server messages


	�


	�

	� 

	�#$

	�%6

	�05
�

� �2N ===================================================================
 Options
2� Each of the definitions above may have "options" attached.  These are
 just annotations which may cause code to be generated slightly differently
 or may contain hints for code that manipulates protocol messages.

 Clients may define custom options as extensions of the *Options messages.
 These extensions may not yet be known at parsing time, so the parser cannot
 store the values in them.  Instead it stores them in a field in the *Options
 message called uninterpreted_option. This field must have the same name
 across all *Options messages. We then use this field to populate the
 extensions when we build a descriptor, at which point all protos have been
 parsed and so all extensions are known.

 Extension numbers for custom options may be chosen as follows:
 * For options which will only be used within a single application or
   organization, or for experimental options, use field numbers 50000
   through 99999.  It is up to you to ensure that you do not use the
   same number for multiple options.
 * For options which will be published and used publicly by multiple
   independent entities, e-mail protobuf-global-extension-registry@google.com
   to reserve extension numbers. Simply provide your project name (e.g.
   Objective-C plugin) and your project website (if available) -- there's no
   need to explain how you intend to use them. Usually you only need one
   extension number. You can declare multiple options with only one extension
   number by putting them in a sub-message. See the Custom Options section of
   the docs for examples:
   https://developers.google.com/protocol-buffers/docs/proto#options
   If this turns out to be popular, a web service will be set up
   to automatically assign option numbers.



�
�

 �#� Sets the Java package where classes generated from this .proto will be
 placed.  By default, the proto package is used, but this is often
 inappropriate because proto packages do not normally start with backwards
 domain names.



 �



 �


 �


 �!"
�

�+� Controls the name of the wrapper Java class generated for the .proto file.
 That class will always contain the .proto file's getDescriptor() method as
 well as any top-level extensions defined in the .proto file.
 If java_multiple_files is disabled, then all the other classes from the
 .proto file will be nested inside the single wrapper outer class.



�



�


�&


�)*
�

�;� If enabled, then the Java code generator will generate a separate .java
 file for each top-level message, enum, and service defined in the .proto
 file.  Thus, these types will *not* be nested inside the wrapper class
 named by java_outer_classname.  However, the wrapper class will still be
 generated to contain the file's getDescriptor() method as well as any
 top-level extensions defined in the file.



�



�


�#


�&(


�):


�49
)

�E This option does nothing.



�



�


�-


�02


�3D


�4C
�

�>� If set true, then the Java2 code generator will generate code that
 throws an exception whenever an attempt is made to assign a non-UTF-8
 byte sequence to a string field.
 Message reflection will do the same.
 However, an extension field still accepts non-UTF-8 byte sequences.
 This option has no effect on when used with the lite runtime.



�



�


�&


�)+


�,=


�7<
L

 ��< Generated classes can be optimized for speed or code size.



 �
D

  �"4 Generate complete code for parsing, serialization,



  �	


  �
G

 � etc.
"/ Use ReflectionOps to implement these methods.



 �


 �
G

 �"7 Generate code using MessageLite and the lite runtime.



 �


 �


�;


�



�


�$


�'(


�):


�49
�

�"� Sets the Go package where structs generated from this .proto will be
 placed. If omitted, the Go package will be derived from the following:
   - The basename of the package import path, if provided.
   - Otherwise, the package statement in the .proto file, if present.
   - Otherwise, the basename of the .proto file, without extension.



�



�


�


�!
�

�;� Should generic services be generated in each language?  "Generic" services
 are not specific to any particular RPC system.  They are generated by the
 main code generators in each language (without additional plugins).
 Generic services were the only kind of service generation supported by
 early versions of google.protobuf.

 Generic services are now considered deprecated in favor of using plugins
 that generate code specific to your particular RPC system.  Therefore,
 these default to false.  Old code which depends on generic services should
 explicitly set them to true.



�



�


�#


�&(


�):


�49


�=


�



�


�%


�(*


�+<


�6;


	�;


	�



	�


	�#


	�&(


	�):


	�49



�<



�




�



�$



�')



�*;



�5:
�

�2� Is this file deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for everything in the file, or it will be completely ignored; in the very
 least, this is a formalization for deprecating files.



�



�


�


�


� 1


�+0


�7q Enables the use of arenas for the proto messages in this file. This applies
 only to generated classes for C++.



�



�


� 


�#%


�&6


�15
�

�)� Sets the objective c class prefix which is prepended to all objective c
 generated classes from this .proto. There is no default.



�



�


�#


�&(
I

�(; Namespace for generated classes; defaults to the package.



�



�


�"


�%'
�

�$� By default Swift generators will take the proto package and CamelCase it
 replacing '.' with underscore and use that to prefix the types/symbols
 defined. When this options is provided, they will use this value instead
 to prefix the types/symbols defined.



�



�


�


�!#
~

�(p Sets the php class prefix which is prepended to all php generated classes
 from this .proto. Default is empty.



�



�


�"


�%'
�

�%� Use this option to change the namespace of php generated classes. Default
 is empty. When this option is empty, the package name will be used for
 determining the namespace.



�



�


�


�"$
�

�.� Use this option to change the namespace of php generated metadata classes.
 Default is empty. When this option is empty, the proto file name will be
 used for determining the namespace.



�



�


�(


�+-
�

�$� Use this option to change the package of ruby generated classes. Default
 is empty. When this option is not set, the package name will be used for
 determining the ruby package.



�



�


�


�!#
=

�$/ Any features defined in the specific edition.



�



�


�


�!#
|

�:n The parser stores options it doesn't recognize here.
 See the documentation for the "Options" section above.



�



�


�3


�69
�

�z Clients can define custom options in extensions of this message.
 See the documentation for the "Options" section above.



 �


 �


 �


	�


	 �


	 �


	 �

� �

�
�
 �>� Set true to use the old proto1 MessageSet wire format for extensions.
 This is provided for backwards-compatibility with the MessageSet wire
 format.  You should not use this for any other reason:  It's less
 efficient, has fewer features, and is more complicated.

 The message must be defined exactly as follows:
   message Foo {
     option message_set_wire_format = true;
     extensions 4 to max;
   }
 Note that the message cannot have any defined fields; MessageSets only
 have extensions.

 All extensions of your type must be singular messages; e.g. they cannot
 be int32s, enums, or repeated messages.

 Because this is an option, the above two restrictions are not enforced by
 the protocol compiler.


 �


 �

 �'

 �*+

 �,=

 �7<
�
�F� Disables the generation of the standard "descriptor()" accessor, which can
 conflict with a field of the same name.  This is meant to make migration
 from proto1 easier; new code should avoid fields named "descriptor".


�


�

�/

�23

�4E

�?D
�
�1� Is this message deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for the message, or it will be completely ignored; in the very least,
 this is a formalization for deprecating messages.


�


�

�

�

�0

�*/

	�

	 �

	 �

	 �

	�

	�

	�

	�

	�

	�
�
�� NOTE: Do not set the option in .proto files. Always use the maps syntax
 instead. The option should only be implicitly set by the proto compiler
 parser.

 Whether the message is an automatically generated map entry type for the
 maps field.

 For maps fields:
     map<KeyType, ValueType> map_field = 1;
 The parsed descriptor looks like:
     message MapFieldEntry {
         option map_entry = true;
         optional KeyType key = 1;
         optional ValueType value = 2;
     }
     repeated MapFieldEntry map_field = 1;

 Implementations may choose not to generate the map_entry=true message, but
 use a native map in the target language to hold the keys and values.
 The reflection APIs in such implementations still need to work as
 if the field is a repeated message field.


�


�

�

�
$
	�" javalite_serializable


	�

	�

	�

	�" javanano_as_lite


	�

	�

	�
�
�P� Enable the legacy handling of JSON field name conflicts.  This lowercases
 and strips underscored from the fields before comparison in proto3 only.
 The new behavior takes `json_name` into account and applies to proto2 as
 well.

 This should only be used as a temporary measure against broken builds due
 to the change in behavior for JSON field name conflicts.

 TODO This is legacy behavior we plan to remove once downstream
 teams have had time to migrate.


�


�

�6

�9;

�<O

�=N
=
�$/ Any features defined in the specific edition.


�


�

�

�!#
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

� �

�
�
 �.� The ctype option instructs the C++ code generator to use a different
 representation of the field than it normally would.  See the specific
 options below.  This option is only implemented to support use of
 [ctype=CORD] and [ctype=STRING] (the default) on non-repeated fields of
 type "bytes" in the open source release -- sorry, we'll try to include
 other types in a future version!


 �


 �

 �

 �

 �-

 �&,

 ��

 �

  � Default mode.


  �


  �
�
 �� The option [ctype=CORD] may be applied to a non-repeated field of type
 "bytes". It indicates that in C++, the data should be stored in a Cord
 instead of a string.  For very large strings, this may reduce memory
 fragmentation. It may also allow better performance when parsing from a
 Cord, or when parsing with aliasing enabled, as the parsed Cord may then
 alias the original buffer.


 �

 �

 �

 �

 �
�
�� The packed option can be enabled for repeated primitive fields to enable
 a more efficient representation on the wire. Rather than repeatedly
 writing the tag and type for each element, the entire array is encoded as
 a single length-delimited blob. In proto3, only explicit setting it to
 false will avoid using packed encoding.  This option is prohibited in
 Editions, but the `repeated_field_encoding` feature can be used to control
 the behavior.


�


�

�

�
�
�3� The jstype option determines the JavaScript type used for values of the
 field.  The option is permitted only for 64 bit integral and fixed types
 (int64, uint64, sint64, fixed64, sfixed64).  A field with jstype JS_STRING
 is represented as JavaScript string, which avoids loss of precision that
 can happen when a large value is converted to a floating point JavaScript.
 Specifying JS_NUMBER for the jstype causes the generated JavaScript code to
 use the JavaScript "number" type.  The behavior of the default option
 JS_NORMAL is implementation dependent.

 This option is an enum to permit additional types to be added, e.g.
 goog.math.Integer.


�


�

�

�

�2

�(1

��

�
'
 � Use the default type.


 �

 �
)
� Use JavaScript strings.


�

�
)
� Use JavaScript numbers.


�

�
�
�+� Should this field be parsed lazily?  Lazy applies only to message-type
 fields.  It means that when the outer message is initially parsed, the
 inner message's contents will not be parsed but instead stored in encoded
 form.  The inner message will actually be parsed when it is first accessed.

 This is only a hint.  Implementations are free to choose whether to use
 eager or lazy parsing regardless of the value of this option.  However,
 setting this option true suggests that the protocol author believes that
 using lazy parsing on this field is worth the additional bookkeeping
 overhead typically needed to implement it.

 This option does not affect the public interface of any generated code;
 all method signatures remain the same.  Furthermore, thread-safety of the
 interface is not affected by this option; const methods remain safe to
 call from multiple threads concurrently, while non-const methods continue
 to require exclusive access.

 Note that implementations may choose not to check required fields within
 a lazy sub-message.  That is, calling IsInitialized() on the outer message
 may return true even if the inner message has missing required fields.
 This is necessary because otherwise the inner message would have to be
 parsed in order to perform the check, defeating the purpose of lazy
 parsing.  An implementation which chooses not to check required fields
 must be consistent about it.  That is, for any particular sub-message, the
 implementation must either *always* check its required fields, or *never*
 check its required fields, regardless of whether or not the message has
 been parsed.

 As of May 2022, lazy verifies the contents of the byte stream during
 parsing.  An invalid byte stream will cause the overall parsing to fail.


�


�

�

�

�*

�$)
�
�7� unverified_lazy does no correctness checks on the byte stream. This should
 only be used where lazy with verification is prohibitive for performance
 reasons.


�


�

�

�"$

�%6

�05
�
�1� Is this field deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for accessors, or it will be completely ignored; in the very least, this
 is a formalization for deprecating fields.


�


�

�

�

�0

�*/
?
�,1 For Google-internal migration only. Do not use.


�


�

�

�

�+

�%*
�
�4� Indicate that the field value should not be printed out when using debug
 formats, e.g. when the field contains sensitive credentials.


�


�

�

�!

�"3

�-2
�
��� If set to RETENTION_SOURCE, the option will be omitted from the binary.
 Note: as of January 2023, support for this is in progress and does not yet
 have an effect (b/264593489).


�

 �

 �

 �

�

�

�

�

�

�

�*

�


�

�$

�')
�
��� This indicates the types of entities that the field may apply to when used
 as an option. If it is unset, then the field may be freely used as an
 option on any kind of entity. Note: as of January 2023, support for this is
 in progress and does not yet have an effect (b/264593489).


�

 �

 �

 �

�

�

�

�$

�

�"#

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

�

	�

	�

	�

	�)

	�


	�

	�#

	�&(

 ��

 �


  �!

  �

  �

  �

  � 
"
 �" Textproto value.


 �

 �

 �

 �


�0


�



�


�*


�-/
=
�$/ Any features defined in the specific edition.


�


�

�

�!#
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

	�" removed jtype


	 �

	 �

	 �
9
	�", reserve target, target_obsolete_do_not_use


	�

	�

	�

� �

�
=
 �#/ Any features defined in the specific edition.


 �


 �

 �

 �!"
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

� �

�
`
 � R Set this option to true to allow mapping different tag names to the same
 value.


 �


 �

 �

 �
�
�1� Is this enum deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for the enum, or it will be completely ignored; in the very least, this
 is a formalization for deprecating enums.


�


�

�

�

�0

�*/

	�" javanano_as_lite


	 �

	 �

	 �
�
�O� Enable the legacy handling of JSON field name conflicts.  This lowercases
 and strips underscored from the fields before comparison in proto3 only.
 The new behavior takes `json_name` into account and applies to proto2 as
 well.
 TODO Remove this legacy behavior once downstream teams have
 had time to migrate.


�


�

�6

�9:

�;N

�<M
=
�#/ Any features defined in the specific edition.


�


�

�

�!"
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

� �

�
�
 �1� Is this enum value deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for the enum value, or it will be completely ignored; in the very least,
 this is a formalization for deprecating enum values.


 �


 �

 �

 �

 �0

 �*/
=
�#/ Any features defined in the specific edition.


�


�

�

�!"
�
�3� Indicate that fields annotated with this enum value should not be printed
 out when using debug formats, e.g. when the field contains sensitive
 credentials.


�


�

�

� 

�!2

�,1
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

� �

�
=
 �$/ Any features defined in the specific edition.


 �


 �

 �

 �!#
�
�2� Is this service deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for the service, or it will be completely ignored; in the very least,
 this is a formalization for deprecating services.
2� Note:  Field numbers 1 through 32 are reserved for Google's internal RPC
   framework.  We apologize for hoarding these numbers to ourselves, but
   we were already using them long before we decided to release Protocol
   Buffers.


�


�

�

�

� 1

�+0
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �

� �

�
�
 �2� Is this method deprecated?
 Depending on the target platform, this can emit Deprecated annotations
 for the method, or it will be completely ignored; in the very least,
 this is a formalization for deprecating methods.
2� Note:  Field numbers 1 through 32 are reserved for Google's internal RPC
   framework.  We apologize for hoarding these numbers to ourselves, but
   we were already using them long before we decided to release Protocol
   Buffers.


 �


 �

 �

 �

 � 1

 �+0
�
 ��� Is this method side-effect-free (or safe in HTTP parlance), or idempotent,
 or neither? HTTP based RPC implementation may choose GET verb for safe
 methods, and PUT verb for idempotent methods instead of the default POST.


 �

  �

  �

  �
$
 �" implies idempotent


 �

 �
7
 �"' idempotent, but may have side effects


 �

 �

��&

�


�

�-

�02

�%

�$
=
�$/ Any features defined in the specific edition.


�


�

�

�!#
O
�:A The parser stores options it doesn't recognize here. See above.


�


�

�3

�69
Z
�M Clients can define custom options in extensions of this message. See above.


 �

 �

 �
�
� �� A message representing a option the parser does not recognize. This only
 appears in options protos created by the compiler::Parser class.
 DescriptorPool resolves these when building Descriptor objects. Therefore,
 options protos in descriptor objects (e.g. returned by Descriptor::options(),
 or produced by Descriptor::CopyTo()) will never have UninterpretedOptions
 in them.


�
�
 ��� The name of the uninterpreted option.  Each string represents a segment in
 a dot-separated name.  is_extension is true iff a segment represents an
 extension (denoted with parentheses in options specs in .proto files).
 E.g.,{ ["foo", false], ["bar.baz", true], ["moo", false] } represents
 "foo.(bar.baz).moo".


 �


  �"

  �

  �

  �

  � !

 �#

 �

 �

 �

 �!"

 �

 �


 �

 �

 �
�
�'� The value of the uninterpreted option, in whatever type the tokenizer
 identified it as during parsing. Exactly one of these should be set.


�


�

�"

�%&

�)

�


�

�$

�'(

�(

�


�

�#

�&'

�#

�


�

�

�!"

�"

�


�

�

� !

�&

�


�

�!

�$%
�
� �� TODO Enums in C++ gencode (and potentially other languages) are
 not well scoped.  This means that each of the feature enums below can clash
 with each other.  The short names we've chosen maximize call-site
 readability, but leave us very open to this scenario.  A future feature will
 be designed and implemented to handle this, hopefully before we ever hit a
 conflict here.
2O ===================================================================
 Features


�

 ��

 �

  �

  �

  �

 �

 �

 �

 �

 �

 �

 �

 �

 �

 ��

 �


 �

 �'

 �*+

 �,�

 �!

  �

 �

  �E

 �E

 �C

��

�

 �

 �

 �

�

�

�

�

�


�

��

�


�

�

� !

�"�

�!

 �

�

 �C

�A

��

�

 �(

 �#

 �&'

�

�


�

�

�

�

��

�


� 

�!8

�;<

�=�

�!

 �

�

 �E

�C

��

�

 � 

 �

 �

�

�

�

�

�


�

��

�


�

�)

�,-

�.�

�!

 �

�

 �A

�C

��

�

 �!

 �

 � 

�

�

�

�

�

�

��

�


�

�+

�./

�0�

�!

 �

�

 �L

��

�

 �

 �

 �

�

�	

�

�

�

�

��

�


�

�!

�$%

�&�

�!

 �!

�

�

 �O

�B

	�

	 �

	 �

	 �

�" for Protobuf C++


 �

 �

 �
 
�" for Protobuf Java


�

�

�
#
�" For internal testing


�

�

�
�
� �� A compiled specification for the defaults of a set of features.  These
 messages are generated from FeatureSet extensions and can be used to seed
 feature resolution. The resolution with this object becomes a simple search
 for the closest matching edition, followed by proto merges.


�
�
 ��� A map from every known edition with a unique set of defaults to its
 defaults. Not all editions may be contained here.  For a given edition,
 the defaults at the closest matching edition ordered at or before it should
 be used.  This field must be in strict ascending order by edition.


 �
"

  �!

  �

  �

  �

  � 

 �%

 �

 �

 � 

 �#$

 �1

 �


 �#

 �$,

 �/0
�
�'t The minimum supported edition (inclusive) when this was constructed.
 Editions before this will not have defaults.


�


�

�"

�%&
�
�'x The maximum known edition (inclusive) when this was constructed. Editions
 after this will not have reliable defaults.


�


�

�"

�%&
�
� �	j Encapsulates information about the original source file from which a
 FileDescriptorProto was generated.
2` ===================================================================
 Optional source code info


�
�
 �!� A Location identifies a piece of source code in a .proto file which
 corresponds to a particular definition.  This information is intended
 to be useful to IDEs, code indexers, documentation generators, and similar
 tools.

 For example, say we have a file like:
   message Foo {
     optional string foo = 1;
   }
 Let's look at just the field definition:
   optional string foo = 1;
   ^       ^^     ^^  ^  ^^^
   a       bc     de  f  ghi
 We have the following locations:
   span   path               represents
   [a,i)  [ 4, 0, 2, 0 ]     The whole field definition.
   [a,b)  [ 4, 0, 2, 0, 4 ]  The label (optional).
   [c,d)  [ 4, 0, 2, 0, 5 ]  The type (string).
   [e,f)  [ 4, 0, 2, 0, 1 ]  The name (foo).
   [g,h)  [ 4, 0, 2, 0, 3 ]  The number (1).

 Notes:
 - A location may refer to a repeated field itself (i.e. not to any
   particular index within it).  This is used whenever a set of elements are
   logically enclosed in a single code segment.  For example, an entire
   extend block (possibly containing multiple extension definitions) will
   have an outer location whose path refers to the "extensions" repeated
   field without an index.
 - Multiple locations may have the same path.  This happens when a single
   logical declaration is spread out across multiple places.  The most
   obvious example is the "extend" block again -- there may be multiple
   extend blocks in the same scope, each of which will have the same path.
 - A location's span is not always a subset of its parent's span.  For
   example, the "extendee" of an extension declaration appears at the
   beginning of the "extend" block and is shared by all extensions within
   the block.
 - Just because a location's span is a subset of some other location's span
   does not mean that it is a descendant.  For example, a "group" defines
   both a type and a field in a single declaration.  Thus, the locations
   corresponding to the type and field and their components will overlap.
 - Code which tries to interpret locations should probably be designed to
   ignore those that it doesn't understand, as more types of locations could
   be recorded in the future.


 �


 �

 �

 � 

 ��	

 �

�
  �,� Identifies which part of the FileDescriptorProto was defined at this
 location.

 Each element is a field number or an index.  They form a path from
 the root FileDescriptorProto to the place where the definition occurs.
 For example, this path:
   [ 4, 3, 2, 7, 1 ]
 refers to:
   file.message_type(3)  // 4, 3
       .field(7)         // 2, 7
       .name()           // 1
 This is because FileDescriptorProto.message_type has field number 4:
   repeated DescriptorProto message_type = 4;
 and DescriptorProto.field has field number 2:
   repeated FieldDescriptorProto field = 2;
 and FieldDescriptorProto.name has field number 1:
   optional string name = 1;

 Thus, the above path gives the location of a field name.  If we removed
 the last element:
   [ 4, 3, 2, 7 ]
 this path refers to the whole field declaration (from the beginning
 of the label to the terminating semicolon).


  �

  �

  �

  �

  �+

  �*
�
 �,� Always has exactly three or four elements: start line, start column,
 end line (optional, otherwise assumed same as start line), end column.
 These are packed into a single field for efficiency.  Note that line
 and column numbers are zero-based -- typically you will want to add
 1 to each before displaying to a user.


 �

 �

 �

 �

 �+

 �*
�
 �	)� If this SourceCodeInfo represents a complete declaration, these are any
 comments appearing before and after the declaration which appear to be
 attached to the declaration.

 A series of line comments appearing on consecutive lines, with no other
 tokens appearing on those lines, will be treated as a single comment.

 leading_detached_comments will keep paragraphs of comments that appear
 before (but not connected to) the current element. Each paragraph,
 separated by empty lines, will be one comment element in the repeated
 field.

 Only the comment content is provided; comment markers (e.g. //) are
 stripped out.  For block comments, leading whitespace and an asterisk
 will be stripped from the beginning of each line other than the first.
 Newlines are included in the output.

 Examples:

   optional int32 foo = 1;  // Comment attached to foo.
   // Comment attached to bar.
   optional int32 bar = 2;

   optional string baz = 3;
   // Comment attached to baz.
   // Another line attached to baz.

   // Comment attached to moo.
   //
   // Another line attached to moo.
   optional double moo = 4;

   // Detached comment for corge. This is not leading or trailing comments
   // to moo or corge because there are blank lines separating it from
   // both.

   // Detached comment for corge paragraph 2.

   optional string corge = 5;
   /* Block comment attached
    * to corge.  Leading asterisks
    * will be removed. */
   /* Block comment attached to
    * grault. */
   optional int32 grault = 6;

   // ignored detached comments.


 �	

 �	

 �	$

 �	'(

 �	*

 �	

 �	

 �	%

 �	()

 �	2

 �	

 �	

 �	-

 �	01
�
�	 �	� Describes the relationship between generated code and its original source
 file. A GeneratedCodeInfo message is associated with only one generated
 source file, but may contain references to different source .proto files.


�	
x
 �	%j An Annotation connects some span of text in generated code to an element
 of its generating .proto file.


 �	


 �	

 �	 

 �	#$

 �	�	

 �	

�
  �	, Identifies the element in the original source .proto file. This field
 is formatted the same as SourceCodeInfo.Location.path.


  �	

  �	

  �	

  �	

  �	+

  �	*
O
 �	$? Identifies the filesystem path to the original source .proto.


 �	

 �	

 �	

 �	"#
w
 �	g Identifies the starting offset in bytes in the generated code
 that relates to the identified object.


 �	

 �	

 �	

 �	
�
 �	� Identifies the ending offset in bytes in the generated code that
 relates to the identified object. The end offset should be one past
 the last relevant byte (so the length of the text = end - begin).


 �	

 �	

 �	

 �	
j
  �	�	X Represents the identified object's effect on the element in the original
 .proto file.


  �		
F
   �	4 There is no effect or the effect is indescribable.


	   �	


	   �	
<
  �	* The element is set or otherwise mutated.


	  �		

	  �	
8
  �	& An alias to the element is returned.


	  �	

	  �	

 �	#

 �	

 �	

 �	

 �	!"
�
google/api/annotations.proto
google.apigoogle/api/http.proto google/protobuf/descriptor.proto:K
http.google.protobuf.MethodOptions�ʼ" (2.google.api.HttpRuleRhttpBn
com.google.apiBAnnotationsProtoPZAgoogle.golang.org/genproto/googleapis/api/annotations;annotations�GAPIJ�
 
�
 2� Copyright 2015 Google LLC

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.


 
	
  
	
 *

 X
	
 X

 "
	

 "

 1
	
 1

 '
	
 '

 "
	
$ "
	
 

  See `HttpRule`.



 $


 



 


 bproto3
�"
google/protobuf/struct.protogoogle.protobuf"�
Struct;
fields (2#.google.protobuf.Struct.FieldsEntryRfieldsQ
FieldsEntry
key (	Rkey,
value (2.google.protobuf.ValueRvalue:8"�
Value;

null_value (2.google.protobuf.NullValueH R	nullValue#
number_value (H RnumberValue#
string_value (	H RstringValue

bool_value (H R	boolValue<
struct_value (2.google.protobuf.StructH RstructValue;

list_value (2.google.protobuf.ListValueH R	listValueB
kind";
	ListValue.
values (2.google.protobuf.ValueRvalues*
	NullValue

NULL_VALUE B
com.google.protobufBStructProtoPZ/google.golang.org/protobuf/types/known/structpb��GPB�Google.Protobuf.WellKnownTypesJ�
 ^
�
 2� Protocol Buffers - Google's data interchange format
 Copyright 2008 Google Inc.  All rights reserved.
 https://developers.google.com/protocol-buffers/

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are
 met:

     * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above
 copyright notice, this list of conditions and the following disclaimer
 in the documentation and/or other materials provided with the
 distribution.
     * Neither the name of Google Inc. nor the names of its
 contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


  

" 
	
" 

# F
	
# F

$ ,
	
$ ,

% ,
	
% ,

& "
	

& "

' !
	
$' !

( ;
	
%( ;
�
 2 5� `Struct` represents a structured data value, consisting of fields
 which map to dynamically typed values. In some languages, `Struct`
 might be supported by a native representation. For example, in
 scripting languages like JS a struct is represented as an
 object. The details of that representation are described together
 with the proto support for the language.

 The JSON representation for `Struct` is JSON object.



 2
9
  4 , Unordered map of dynamically typed values.


  4

  4

  4
�
= M� `Value` represents a dynamically typed value which can be either
 null, a number, a string, a boolean, a recursive struct value, or a
 list of values. A producer of value is expected to set one of these
 variants. Absence of any variant indicates an error.

 The JSON representation for `Value` is JSON value.



=
"
 ?L The kind of value.


 ?
'
 A Represents a null value.


 A

 A

 A
)
C Represents a double value.


C


C

C
)
E Represents a string value.


E


E

E
*
G Represents a boolean value.


G

G	

G
-
I  Represents a structured value.


I


I

I
-
K  Represents a repeated `Value`.


K

K

K
�
 S V� `NullValue` is a singleton enumeration to represent the null value for the
 `Value` type union.

 The JSON representation for `NullValue` is JSON `null`.



 S

  U Null value.


  U

  U
�
[ ^v `ListValue` is a wrapper around a repeated field of values.

 The JSON representation for `ListValue` is JSON array.



[
:
 ]- Repeated field of dynamically typed values.


 ]


 ]

 ]

 ]bproto3
�1
google/protobuf/timestamp.protogoogle.protobuf";
	Timestamp
seconds (Rseconds
nanos (RnanosB�
com.google.protobufBTimestampProtoPZ2google.golang.org/protobuf/types/known/timestamppb��GPB�Google.Protobuf.WellKnownTypesJ�/
 �
�
 2� Protocol Buffers - Google's data interchange format
 Copyright 2008 Google Inc.  All rights reserved.
 https://developers.google.com/protocol-buffers/

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are
 met:

     * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above
 copyright notice, this list of conditions and the following disclaimer
 in the documentation and/or other materials provided with the
 distribution.
     * Neither the name of Google Inc. nor the names of its
 contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


  

" 
	
" 

# I
	
# I

$ ,
	
$ ,

% /
	
% /

& "
	

& "

' !
	
$' !

( ;
	
%( ;
�
 � �� A Timestamp represents a point in time independent of any time zone or local
 calendar, encoded as a count of seconds and fractions of seconds at
 nanosecond resolution. The count is relative to an epoch at UTC midnight on
 January 1, 1970, in the proleptic Gregorian calendar which extends the
 Gregorian calendar backwards to year one.

 All minutes are 60 seconds long. Leap seconds are "smeared" so that no leap
 second table is needed for interpretation, using a [24-hour linear
 smear](https://developers.google.com/time/smear).

 The range is from 0001-01-01T00:00:00Z to 9999-12-31T23:59:59.999999999Z. By
 restricting to that range, we ensure that we can convert to and from [RFC
 3339](https://www.ietf.org/rfc/rfc3339.txt) date strings.

 # Examples

 Example 1: Compute Timestamp from POSIX `time()`.

     Timestamp timestamp;
     timestamp.set_seconds(time(NULL));
     timestamp.set_nanos(0);

 Example 2: Compute Timestamp from POSIX `gettimeofday()`.

     struct timeval tv;
     gettimeofday(&tv, NULL);

     Timestamp timestamp;
     timestamp.set_seconds(tv.tv_sec);
     timestamp.set_nanos(tv.tv_usec * 1000);

 Example 3: Compute Timestamp from Win32 `GetSystemTimeAsFileTime()`.

     FILETIME ft;
     GetSystemTimeAsFileTime(&ft);
     UINT64 ticks = (((UINT64)ft.dwHighDateTime) << 32) | ft.dwLowDateTime;

     // A Windows tick is 100 nanoseconds. Windows epoch 1601-01-01T00:00:00Z
     // is 11644473600 seconds before Unix epoch 1970-01-01T00:00:00Z.
     Timestamp timestamp;
     timestamp.set_seconds((INT64) ((ticks / 10000000) - 11644473600LL));
     timestamp.set_nanos((INT32) ((ticks % 10000000) * 100));

 Example 4: Compute Timestamp from Java `System.currentTimeMillis()`.

     long millis = System.currentTimeMillis();

     Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
         .setNanos((int) ((millis % 1000) * 1000000)).build();

 Example 5: Compute Timestamp from Java `Instant.now()`.

     Instant now = Instant.now();

     Timestamp timestamp =
         Timestamp.newBuilder().setSeconds(now.getEpochSecond())
             .setNanos(now.getNano()).build();

 Example 6: Compute Timestamp from current time in Python.

     timestamp = Timestamp()
     timestamp.GetCurrentTime()

 # JSON Mapping

 In JSON format, the Timestamp type is encoded as a string in the
 [RFC 3339](https://www.ietf.org/rfc/rfc3339.txt) format. That is, the
 format is "{year}-{month}-{day}T{hour}:{min}:{sec}[.{frac_sec}]Z"
 where {year} is always expressed using four digits while {month}, {day},
 {hour}, {min}, and {sec} are zero-padded to two digits each. The fractional
 seconds, which can go up to 9 digits (i.e. up to 1 nanosecond resolution),
 are optional. The "Z" suffix indicates the timezone ("UTC"); the timezone
 is required. A proto3 JSON serializer should always use UTC (as indicated by
 "Z") when printing the Timestamp type and a proto3 JSON parser should be
 able to accept both UTC and other timezones (as indicated by an offset).

 For example, "2017-01-15T01:30:15.01Z" encodes 15.01 seconds past
 01:30 UTC on January 15, 2017.

 In JavaScript, one can convert a Date object to this format using the
 standard
 [toISOString()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/toISOString)
 method. In Python, a standard `datetime.datetime` object can be converted
 to this format using
 [`strftime`](https://docs.python.org/2/library/time.html#time.strftime) with
 the time format spec '%Y-%m-%dT%H:%M:%S.%fZ'. Likewise, in Java, one can use
 the Joda Time's [`ISODateTimeFormat.dateTime()`](
 http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISODateTimeFormat.html#dateTime()
 ) to obtain a formatter capable of generating timestamps in this format.



 �
�
  �� Represents seconds of UTC time since Unix epoch
 1970-01-01T00:00:00Z. Must be from 0001-01-01T00:00:00Z to
 9999-12-31T23:59:59Z inclusive.


  �

  �

  �
�
 �� Non-negative fractions of a second at nanosecond resolution. Negative
 second values with fractions must still have non-negative nanos values
 that count forward in time. Must be from 0 to 999,999,999
 inclusive.


 �

 �

 �bproto3
�
pos_homologation.protopos.homologationgoogle/api/annotations.protogoogle/protobuf/struct.protogoogle/protobuf/timestamp.protobase_data_type.protocore_functionality.proto"
GetSystemInfoRequest"�

SystemInfo=
date_version (2.google.protobuf.TimestampRdateVersion!
main_version (	RmainVersion5
implementation_version (	RimplementationVersion"�
SimulateProcessOrderRequest
pos_id (RposId
pos_uuid (	RposUuid
id (Rid'
create_payments (RcreatePayments$
is_open_refund (RisOpenRefundB
payments (2&.pos.homologation.CreatePaymentRequestRpayments"�
CreatePaymentRequest
uuid (	Ruuid
id (Rid
pos_uuid (	RposUuid

order_uuid (	R	orderUuid!
invoice_uuid (	RinvoiceUuid
	bank_uuid (	RbankUuid!
reference_no (	RreferenceNo 
description	 (	Rdescription
amount
 (	Ramount!
payment_date (	RpaymentDate(
tender_type_code (	RtenderTypeCode#
currency_uuid (	RcurrencyUuid.
payment_method_uuid (	RpaymentMethodUuid0
payment_account_date (	RpaymentAccountDate
	is_refund (RisRefund
charge_uuid (	R
chargeUuid2
collecting_agent_uuid (	RcollectingAgentUuid=
reference_bank_account_uuid (	RreferenceBankAccountUuid;
customer_bank_account_uuid (	RcustomerBankAccountUuid0
invoice_reference_id (RinvoiceReferenceId"�
Campaign
id (Rid
uuid (	Ruuid
name (	Rname 
description (	Rdescription

start_date (R	startDate
end_date (RendDate">
City
id (Rid
uuid (	Ruuid
name (	Rname"@
Region
id (Rid
uuid (	Ruuid
name (	Rname"�
Address
id (Rid
uuid (	Ruuid0
region (2.pos.homologation.RegionRregion*
city (2.pos.homologation.CityRcity
address1 (	Raddress1
address2 (	Raddress2
address3 (	Raddress3
address4	 (	Raddress4
phone
 (	Rphone
postal_code (	R
postalCode!
country_code (	RcountryCode!
country_uuid (	RcountryUuid

country_id (R	countryId.
is_default_shipping (RisDefaultShipping,
is_default_billing (RisDefaultBilling!
contact_name (	RcontactName
email (	Remail 
description (	Rdescription

first_name (	R	firstName
	last_name (	RlastNameh
additional_attributes (23.pos.homologation.Address.AdditionalAttributesEntryRadditionalAttributesT
AdditionalAttributesEntry
key (	Rkey!
value (2.data.ValueRvalue:8"�
Customer
id (Rid
uuid (	Ruuid
value (	Rvalue
tax_id (	RtaxId
duns (	Rduns
naics (	Rnaics
name (	Rname
	last_name (	RlastName 
description	 (	Rdescription7
	addresses
 (2.pos.homologation.AddressR	addressesi
additional_attributes (24.pos.homologation.Customer.AdditionalAttributesEntryRadditionalAttributesT
AdditionalAttributesEntry
key (	Rkey!
value (2.data.ValueRvalue:8"�	
Order
uuid (	Ruuid
id (Rid
document_no (	R
documentNo7
document_type (2.data.DocumentTypeRdocumentTypeL
sales_representative (2.data.SalesRepresentativeRsalesRepresentative=
document_status (2.data.DocumentStatusRdocumentStatus.

price_list (2.data.PriceListR	priceList-
	warehouse (2.data.WarehouseR	warehouse.
total_lines	 (2.data.DecimalR
totalLines.
grand_total
 (2.data.DecimalR
grandTotal,

tax_amount (2.data.DecimalR	taxAmount6
discount_amount (2.data.DecimalRdiscountAmount!
date_ordered (	RdateOrdered6
customer (2.pos.homologation.CustomerRcustomer!
is_delivered (RisDelivered'
order_reference (	RorderReference 
description (	Rdescription6
campaign (2.pos.homologation.CampaignRcampaignA
display_currency_rate (2.data.DecimalRdisplayCurrencyRate.
open_amount (2.data.DecimalR
openAmount4
payment_amount (2.data.DecimalRpaymentAmount2
refund_amount (2.data.DecimalRrefundAmount2
charge_amount (2.data.DecimalRchargeAmount2
credit_amount (2.data.DecimalRcreditAmount"
source_rma_id (RsourceRmaId
is_rma (RisRma(
is_binding_offer (RisBindingOffer
is_order (RisOrder"�
	OrderLine
uuid (	Ruuid

order_uuid (	R	orderUuid'
product (2.data.ProductRproduct$
charge (2.data.ChargeRcharge)
line_description (	RlineDescription 
description (	Rdescription-
	warehouse (2.data.WarehouseR	warehouse)
quantity (2.data.DecimalRquantity8
quantity_ordered	 (2.data.DecimalRquantityOrdered<
available_quantity
 (2.data.DecimalRavailableQuantity#
price (2.data.DecimalRprice3
price_with_tax (2.data.DecimalRpriceWithTax,

price_base (2.data.DecimalR	priceBase<
price_base_with_tax (2.data.DecimalRpriceBaseWithTax,

price_list (2.data.DecimalR	priceList<
price_list_with_tax (2.data.DecimalRpriceListWithTax2
discount_rate (2.data.DecimalRdiscountRate6
discount_amount (2.data.DecimalRdiscountAmount,

tax_amount (2.data.DecimalR	taxAmount5
base_tax_amount (2.data.DecimalRbaseTaxAmount5
list_tax_amount (2.data.DecimalRlistTaxAmount(
tax_rate (2.data.TaxRateRtaxRateA
total_discount_amount (2.data.DecimalRtotalDiscountAmount7
total_tax_amount (2.data.DecimalRtotalTaxAmount9
total_base_amount (2.data.DecimalRtotalBaseAmountI
total_base_amount_with_tax (2.data.DecimalRtotalBaseAmountWithTax0
total_amount (2.data.DecimalRtotalAmount@
total_amount_with_tax (2.data.DecimalRtotalAmountWithTax
line (Rline)
uom (2.data.ProductConversionRuom8
product_uom (2.data.ProductConversionR
productUom
id! (Rid+
source_rma_line_id" (RsourceRmaLineId"�
ProcessWithoutPrintRequest
pos_id (RposId
pos_uuid (	RposUuid
id (Rid'
create_payments (RcreatePayments$
is_open_refund (RisOpenRefundB
payments (2&.pos.homologation.CreatePaymentRequestRpayments,
fiscal_document_no (	RfiscalDocumentNo7
fiscal_printer_serial_no (	RfiscalPrinterSerialNo

closing_no	 (	R	closingNo

print_date
 (	R	printDate"�
PrintTicketResponse
summary (	Rsummary
is_error (RisError
	file_name (	RfileName
	mime_type (	RmimeType#
output_stream (RoutputStream
result_type (	R
resultType;
result_values (2.google.protobuf.ValueRresultValues2�
PosHomologationService
GetSystemInfo&.pos.homologation.GetSystemInfoRequest.pos.homologation.SystemInfo"(���" /v1/pos/homologation/system-info�
SimulateProcessOrder-.pos.homologation.SimulateProcessOrderRequest%.pos.homologation.PrintTicketResponse">���8"3/v1/pos/homologation/{pos_id}/process/{id}/simulate:*�
ProcessWithoutPrint,.pos.homologation.ProcessWithoutPrintRequest.pos.homologation.Order"5���/"*/v1/pos/homologation/{pos_id}/process/{id}:*B#
org.spin.proto.pos.homologationPJ�T
  �

  

 8
	
 8

 "
	

 "
	
  &
	
 &
	
 )
	
	 
	

 "

 
*
   System Info
" empty request



 


 




 3

 !

 ".

 12

 







*



%

()
2
 !& Request for simulate process a order



#

 

 

 

 









" order identifier








!





 

 







 3

 

 %

 &.

 12


" 9


"

 #

 #

 #

 #

$

$

$

$

%

%

%

%

&

&

&

&

' 

'

'

'

(

(

(

(

) 

)

)

)

*

*

*

*
(
, data.Decimal amount = 10;


,

,

,
1
	.!$ google.protobuf.Value amount = 10;


	.

	.

	. 


/%


/


/


/"$

0"

0

0

0!

1(

1

1"

1%'

2)

2

2#

2&(

3

3

3

3

4 

4

4

4

5*

5

5$

5')

60

6

6*

6-/

7/

7

7)

7,.

8(

8

8"

8%'

= D
 Campaign



=

 >

 >

 >

 >

?

?

?

?

@

@

@

@

A

A

A

A

B

B

B

B

C

C

C

C

H L
 Customer



H

 I

 I

 I

 I

J

J

J

J

K

K

K

K


M Q


M

 N

 N

 N

 N

O

O

O

O

P

P

P

P


R j


R

 S

 S

 S

 S

T

T

T

T

V
	Location


V

V

V

W

W

W

W

X

X

X

X

Y

Y

Y

Y

Z

Z

Z

Z

[

[

[

[

\

\

\

\

	] 

	]

	]

	]


^!


^


^


^ 

_!

_

_

_ 

`

`

`

`

a&

a

a 

a#%

b%

b

b

b"$

c!

c

c

c 

d

d

d

d

e 

e

e

e

f

f

f

f

g

g

g

g
A
h;"4 google.protobuf.Struct additional_attributes = 22;


h

h 5

h8:


k x


k

 l

 l

 l

 l

m

m

m

m

n

n

n

n

o

o

o

o

p

p

p

p

q

q

q

q

r

r

r

r

s

s

s

s

t

t

t

t

	u(

	u

	u

	u"

	u%'
A

v;"4 google.protobuf.Struct additional_attributes = 11;



v


v 5


v8:

	{ � Sales Order



	{

	 |

	 |

	 |

	 |

	}

	}

	}

	}

	~

	~

	~

	~

	,

	

	'

	*+

	�:

	� 

	�!5

	�89

	�0

	�

	�+

	�./

	�&

	�

	�!

	�$%

	�%

	�

	� 

	�#$

	�%

	�

	� 

	�#$
O
		�&A string total_lines = 9;
 google.protobuf.Value total_lines = 9;


		�

		� 

		�#%
Q
	
�%C string grand_total = 10;
 google.protobuf.Value grand_total = 10;


	
�

	
�

	
�"$
O
	�*A string tax_amount = 12;
 google.protobuf.Value tax_amount = 12;


	�

	�$

	�')
J
	�!< string discount_amount = 13;
 string discount_amount = 13;


	�

	�

	� 

	�

	�

	�

	�

	�

	�

	�

	�

	�$

	�

	�

	�!#

	� 

	�

	�

	�

	�

	�

	�

	�

	�0

	�

	�*

	�-/
e
	�&W string display_currency_rate = 20;
 google.protobuf.Value display_currency_rate = 20;


	�

	� 

	�#%
Q
	�)C string open_amount = 21;
 google.protobuf.Value open_amount = 21;


	�

	�#

	�&(
W
	�(I string payment_amount = 22;
 google.protobuf.Value payment_amount = 22;


	�

	�"

	�%'
U
	�(G string refund_amount = 23;
 google.protobuf.Value refund_amount = 23;


	�

	�"

	�%'
U
	�(G string charge_amount = 24;
 google.protobuf.Value charge_amount = 24;


	�

	�"

	�%'
U
	�!G string credit_amount = 25;
 google.protobuf.Value credit_amount = 25;


	�

	�

	� 

	�

	�

	�

	�

	�#

	�

	�

	� "

	�

	�

	�

	�


� �


�


 �


 �


 �


 �


�


�


�


�


�!


�


�


� 


�


�


�


�


�$


�


�


�"#


�


�


�


�


�%


�


� 


�#$


�"


�


�


� !


�*


�


�%


�()


	�-


	�


	�'


	�*,



� 



�



�



�


�)


�


�#


�&(


�%


�


�


�"$


�.


�


�(


�+-


�%


�


�


�"$


�.


�


�(


�+-


�(


�


�"


�%'


�*


�


�$


�')


�%


�


�


�"$


�*


�


�$


�')


�*


�


�$


�')


�#


�


�


� "


�0	Totals



�


�*


�-/


�+


�


�%


�(*


�,


�


�&


�)+


�5


�


�/


�24


�'


�


�!


�$&


�0


�


�*


�-/


�


�


�


�


�(


�


�"


�%'


�0


�


�*


�-/
I

�; time_control.ResourceAssignment resource_assignment = 32;



�


�


�


 �&


 �


 � 


 �#%

� �	Print Ticket


�"

 �

 �

 �

 �

�

�

�

�
 
�" order identifier


�

�

�

�!

�

�

� 

� 

�

�

�

�3

�

�%

�&.

�12
!
�&" overwrite invoice


�

�!

�$%

�,

�

�'

�*+

�"
 Z Report


�

�

�
:
	�, google.protobuf.Timestamp print_date = 10;


	�

	�

	�
$
� �	Response after print


�

 �

 �

 �

 �

�

�

�

�

�

�

�

�

�

�

�

�

� 

�

�

�

�

�

�

�
6
�0( map<string, string> result_values = 7;


�

�+

�./
0
 � �" The greeting service definition.


 �
*
  ��	 Get a System Information


  �

  �.

  �9C

  ��

	  �ʼ"��

 ��	 Process Order


 � 

 �!<

 �GZ

 ��

	 �ʼ"��
'
 ��		Process without Print


 �

 � :

 �EJ

 ��

	 �ʼ"��bproto3