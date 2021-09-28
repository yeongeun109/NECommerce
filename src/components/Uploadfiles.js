import React, { useState } from 'react';
import { Button } from 'react-bootstrap';
import { uploadFile } from 'react-s3';



const UploadImageToS3 = () => {
    const S3_BUCKET = process.env.REACT_APP_S3_BUCKET;
    const REGION = process.env.REACT_APP_REGION;
    const ACCESS_KEY = process.env.REACT_APP_ACCESS_KEY;
    const SECRET_ACCESS_KEY = process.env.REACT_APP_SECRET_ACCESS_KEY;
    // const EXAMPLE = process.env.REACT_APP_EXAMPLE;
    const config = {
        bucketName: S3_BUCKET,
        region: REGION,
        accessKeyId: ACCESS_KEY,
        secretAccessKey: SECRET_ACCESS_KEY
    }

    const [selectedFile, setSelectedFile] = useState(null)

    const handleFileInput = (e) => {
        setSelectedFile(e.target.files[0]);
        // console.log(EXAMPLE)
    }

    const handleUpload = async (file) => {
        console.log(file)
        uploadFile(file, config)
            .then(data => console.log(data))
            .catch(error => console.error(error))
    }

    return (
        <div>
            <h1>S3 File UPLOAD</h1>
            <input type='file' onChange={handleFileInput}/>
            <Button onClick={() => handleUpload(selectedFile)}>Upload</Button>
        </div>
    )
}

export default UploadImageToS3