package ua.kiev.prog;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public class MyController {

    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/all_photos", method = RequestMethod.POST)
    public String onAllPhotos(Model model) {
        Set<Long> keySet = photos.keySet();
        model.addAttribute("photosId", keySet);
        return "all_photos";
    }

    @RequestMapping(value = "/delete_checked", method = RequestMethod.POST)
    public String deleteCheckedFromAll(@RequestParam(value = "checked[]", required = false) Long[] checked) {
        if (checked != null) {
            for (Long id : checked) {
                photos.remove(id);
            }
        }
        return "all_photos";
    }

    @RequestMapping("/download_zip")
    public String downloadZip(HttpServletResponse response) throws IOException {
        File zipFile = zipAllPhotos();
        FileInputStream fis = new FileInputStream(zipFile);
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=photos.zip");
        IOUtils.copy(fis, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return "index";
    }

    public File zipAllPhotos() {
        Set<Long> keySet = photos.keySet();
        File zipFile = new File("/home/maxmlv/Web/photos-webapp-server/photos.zip");
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (Long id : keySet) {
                ZipEntry zipEntry = new ZipEntry(id + ".png");
                zipEntry.setSize(photos.get(id).length);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(photos.get(id));
                zipOutputStream.closeEntry();
            }
            zipOutputStream.close();
            return zipFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
