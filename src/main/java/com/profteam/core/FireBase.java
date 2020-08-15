package com.profteam.core;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class FireBase {
    
    private static FireBase fireBase = null;
    
    private class Constants {
        public static final String PROJECT_ID = "book-garden";
        public static final String BUCKET_NAME = "book-garden.appspot.com";
        public static final String SERVICE_ACCOUNT_KEY = "/firebase/serviceAccountKey.json";
    }
    
    private final String bucketName = Constants.BUCKET_NAME;
    
    private Storage storage;
    
    public FireBase() {
        try {
            initStorage();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public static FireBase getFireBase() {
        return fireBase == null ? new FireBase()
                                : fireBase;
    }
    
    private void initStorage() throws IOException {
        try (FileInputStream serviceAccount = new FileInputStream(FireBase.class.getResource(Constants.SERVICE_ACCOUNT_KEY).getFile())) {
            this.storage = StorageOptions.newBuilder()
                                         .setProjectId(Constants.PROJECT_ID)
                                         .setCredentials(ServiceAccountCredentials.fromStream(serviceAccount))
                                         .build()
                                         .getService();
        }
    }
    
    public void uploadImg(String folderName, File file, String newName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, folderName + "/" + newName);
        if (storage.get(blobId) != null) {
            storage.delete(blobId);
        }
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                                    .setContentType("image/jpeg")
                                    .build();
        
        try (WriteChannel writer = storage.writer(blobInfo);
             InputStream inputStream = new FileInputStream(file)
        ) {
            byte[] buffer = new byte[2000];
            int limit;
            while ((limit = inputStream.read(buffer)) >= 0) {
                writer.write(ByteBuffer.wrap(buffer, 0, limit));
            }
        }
    }
    
    public BufferedImage downloadImg(String folderName, String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, folderName + "/" + fileName);
        byte[] buffer = storage.readAllBytes(blobId);
        return ImageIO.read(new ByteArrayInputStream(buffer));
    }
    
    public void deteleImg(String folderName, String fileName) {
        BlobId blobId = BlobId.of(bucketName, folderName + "/" + fileName);
        storage.delete(blobId);
    }
    
}