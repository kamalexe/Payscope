package pay.scope.payscope.Helper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public Bitmap generateQRCode(String content, Bitmap logo) throws WriterException {
        // Set the size of the QR code
        int size = 400;

        // Encode the content as a QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1); // Adjust the margin of the QR code

        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);

        // Create a bitmap to store the QR code
        Bitmap qrBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);

        // Set the pixels for the QR code
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                qrBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        // Overlay the logo on the QR code
        return overlayLogo(qrBitmap, logo);
    }

    private Bitmap overlayLogo(Bitmap qrBitmap, Bitmap logo) {
        // Create a mutable bitmap to combine QR code and logo
        Bitmap combined = Bitmap.createBitmap(qrBitmap.getWidth(), qrBitmap.getHeight(), qrBitmap.getConfig());
        Canvas canvas = new Canvas(combined);

        // Draw the QR code
        canvas.drawBitmap(qrBitmap, 0, 0, null);

        // Calculate the position to overlay the logo in the center
        float centerX = (qrBitmap.getWidth() - logo.getWidth()) / 2f;
        float centerY = (qrBitmap.getHeight() - logo.getHeight()) / 2f;
        canvas.drawBitmap(logo, centerX, centerY, null);

        return combined;
    }

/*
    private Bitmap overlayLogo(Bitmap qrBitmap, Bitmap logo) {
        // Create a mutable bitmap to combine QR code and logo
        Bitmap combined = Bitmap.createBitmap(qrBitmap.getWidth(), qrBitmap.getHeight(), qrBitmap.getConfig());
        Canvas canvas = new Canvas(combined);

        // Draw the QR code
        canvas.drawBitmap(qrBitmap, 0, 0, null);

        // Define the size for the logo (20% of QR code size)
        int logoWidth = qrBitmap.getWidth() / 8;
        int logoHeight = qrBitmap.getHeight() / 8;

        // Resize the logo to the defined size
        Bitmap scaledLogo = Bitmap.createScaledBitmap(logo, logoWidth, logoHeight, false);

        // Calculate the position to overlay the logo in the center
        float centerX = (qrBitmap.getWidth() - scaledLogo.getWidth()) / 2f;
        float centerY = (qrBitmap.getHeight() - scaledLogo.getHeight()) / 2f;

        // Draw the resized logo on the QR code
        canvas.drawBitmap(scaledLogo, centerX, centerY, null);

        return combined;
    }
 */

}
