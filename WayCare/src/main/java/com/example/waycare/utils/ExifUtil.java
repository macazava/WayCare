    package com.example.waycare.utils;

    import com.drew.imaging.ImageMetadataReader;
    import com.drew.metadata.Metadata;
    import com.drew.metadata.exif.GpsDirectory;
    import com.drew.metadata.exif.ExifSubIFDDirectory;

    import java.io.File;
    import java.time.LocalDate;
    import java.time.ZoneId;
    import java.util.Date;

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
                System.out.println("Erro ao ler Meta-coordenadas EXIF: " + e.getMessage());
            }
            return null;
        }

        public static LocalDate extrairData(File imagem) {
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(imagem);
                ExifSubIFDDirectory exifDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

                if (exifDir != null) {
                    Date dataOriginal = exifDir.getDateOriginal();
                    if (dataOriginal != null) {
                        return dataOriginal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler Meta-datas EXIF: " + e.getMessage());
            }
            return null;
        }
    }
