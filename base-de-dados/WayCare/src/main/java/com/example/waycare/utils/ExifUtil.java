package com.example.waycare.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;

public class ExifUtil {
    public static double[] extrairCoordenadas(File imagem) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imagem);
            GpsDirectory gpsDir = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDir != null && gpsDir.getGeoLocation() != null) {
                double lat = gpsDir.getGeoLocation().getLatitude();
                double lon = gpsDir.getGeoLocation().getLongitude();
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler EXIF: " + e.getMessage());
        }
        return null;
    }
}
