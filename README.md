# SOAP Web Services

### This project includes SOAP web services for managing students and storing photos in MinIO.



## StorageEndpoint

### 1) Saves a photo to MinIO.
- **URL**: `/Service/StorageService`
- **Method**: POST
- **Content-Type**: `text/xml`
**Request Example**:

```xml
<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://service.ws.sample/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:save>
         <file>[BASE64_ENCODED_IMAGE_DATA]</file>
         <fileName>3.jpg</fileName>
         <contentType>image/jpeg</contentType>
      </ws:save>
   </soapenv:Body>
</soapenv:Envelope>
```
**example encoded image data:**
```xml
<file>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</file>
```

```curl
curl --location 'http://localhost:8086/Service/StorageService' \
--header 'Content-Type: text/xml' \
--data '<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://service.ws.sample/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:save>
         <file>[BASE64_ENCODED_FILE_DATA]</file>
         <fileName>3.jpg</fileName>
         <contentType>image/jpeg</contentType>
      </ws:save>
   </soapenv:Body>
</soapenv:Envelope>'
```

### 2) Read Chunk from MinIO.

- **URL**: `/Service/StorageService`
- **Method**: POST
- **Content-Type**: `text/xml`

**Request Example**:

```xml
<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://service.ws.sample/">
    <soapenv:Header/>
    <soapenv:Body>
        <ws:readChunk>
            <range>bytes=0-3145728</range>
            <uuid>3e8e6ac6-7f12-4c87-84f0-e0b91baf47e8</uuid>
        </ws:readChunk>
    </soapenv:Body>
</soapenv:Envelope>
```

```curl
curl --location 'http://localhost:8086/Service/StorageService' \
--header 'Content-Type: text/xml' \
--data '<?xml version="1.0"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://service.ws.sample/">
    <soapenv:Header/>
    <soapenv:Body>
        <ws:readChunk>
            <range>bytes=0-3145728</range>
            <uuid>3d6c599f-0878-4a4d-9e1b-54c25ba05027</uuid>
        </ws:readChunk>
    </soapenv:Body>
</soapenv:Envelope>'
```

## StudentEndpoint

### 3) Create or Update Student.

#### Creates a new student if the ID is not provided, or updates an existing student if the ID is provided.
- **URL**: `/Service/StudentService`
- **Method**: POST
- **Content-Type**: `text/xml`
  **Request Example**:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:createOrUpdateStudent>
            <studentDto>
                <id>12</id>
                <firstName>Clare</firstName>
                <lastName>John</lastName>
                <fatherName>John</fatherName>
                <age>19</age>
                <recordBookNumber>12410</recordBookNumber>
            </studentDto>
        </tns:createOrUpdateStudent>
    </soapenv:Body>
</soapenv:Envelope>
```

```curl
curl --location 'http://localhost:8086/Service/StudentService' \
--header 'Content-Type: text/xml' \
--data '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
   <soapenv:Header/>
   <soapenv:Body>
      <tns:createOrUpdateStudent>
         <studentDto>
            <id>12</id>
            <firstName>Clare</firstName>
            <lastName>John</lastName>
            <fatherName>John</fatherName>
            <age>19</age>
            <recordBookNumber>12410</recordBookNumber>
         </studentDto>
      </tns:createOrUpdateStudent>
   </soapenv:Body>
</soapenv:Envelope>'
```

### 4) Get Student by Record Book Number

#### Retrieves a student by their unique record book number.
- **URL**: `/Service/StudentService`
- **Method**: POST
- **Content-Type**: `text/xml`
  **Request Example**:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:getOneUnit>
            <recordBookNumber>12411</recordBookNumber>
        </tns:getOneUnit>
    </soapenv:Body>
</soapenv:Envelope>
```

```curl
curl --location 'http://localhost:8086/Service/StudentService' \
--header 'Content-Type: text/xml' \
--data '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
   <soapenv:Header/>
   <soapenv:Body>
      <tns:getOneUnit>
        <recordBookNumber>12411</recordBookNumber>
      </tns:getOneUnit>
   </soapenv:Body>
</soapenv:Envelope>'
```

### 5) Get All Students

#### Retrieves all students.
- **URL**: `/Service/StudentService`
- **Method**: POST
- **Content-Type**: `text/xml`
  **Request Example**:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:getAllUnits/>
    </soapenv:Body>
</soapenv:Envelope>
```

```curl
curl --location 'http://localhost:8086/Service/StudentService' \
--header 'Content-Type: text/xml' \
--data '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://service.ws.sample/">
   <soapenv:Header/>
   <soapenv:Body>
      <tns:getAllUnits></tns:getAllUnits>
   </soapenv:Body>
</soapenv:Envelope>'
```

