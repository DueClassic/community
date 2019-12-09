package com.cc.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.BucketAuthorization;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileBucketLocalAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.cc.community.exception.CustomizeErrorCode;
import com.cc.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by xiaomi on 2019/12/9.
 */
@Service
public class UCloudProvider {
    @Value("${ucloud.ufile.public-key}")
    private String publicKey;
    @Value("${ucloud.ufile.private-key}")
    private String privateKey;
    @Value("${ucloud.ufile.bucket-name}")
    private String bucketName;
    @Value("ucloud.ufile.region")
    private String region;
    @Value("ucloud.ufile.suffix")
    private String suffix;
    @Value("ucloud.ufile.expires")
    private Integer expires;

    public String upload(InputStream fileStream,String mimeType,String fileName){
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length>1){
            generatedFileName= UUID.randomUUID().toString()+"."+filePaths[filePaths.length-1];
        }else{
            throw new CustomizeException(CustomizeErrorCode.FAIL_UPLOAD_FILE);
        }
        try {
            ObjectAuthorization  OBJECT_AUTHORIZER  = new UfileObjectLocalAuthorization(publicKey,privateKey);
            ObjectConfig config = new ObjectConfig(region,suffix);
            bucketName = "";
            PutObjectResultBean response = UfileClient.object(OBJECT_AUTHORIZER, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generatedFileName)
                    .toBucket(bucketName)
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {}
                    })
                    .execute();
            if (response!=null&&response.getRetCode()==0){
                String url=UfileClient.object(OBJECT_AUTHORIZER,config)
                        .getDownloadUrlFromPrivateBucket(generatedFileName,bucketName,expires)
                        .createUrl();
                return url;
            }else{
                throw new CustomizeException(CustomizeErrorCode.FAIL_UPLOAD_FILE);
            }
        } catch (UfileClientException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FAIL_UPLOAD_FILE);
        } catch (UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FAIL_UPLOAD_FILE);
        }
    }
}
