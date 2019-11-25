package com.nagarro.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Image;
import com.nagarro.models.Product;
import com.nagarro.models.Seller;
import com.nagarro.repositories.ImageRepository;
import com.nagarro.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	Product product;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	Seller seller;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	Image image;

	private static final String IMAGE_UPLOAD_PATH = "D:\\Upload\\";
	private static final String DOC_UPLOAD_PATH = "D:\\docs\\";
	
	public Product findByProductId(String id) {
		Product product = productRepository.getOne(id);
		return product;
	}
	

	public Product addProduct(HttpServletRequest request) {

		String fileLocation;
		int code = 200;
		HashMap<String, String> hm = new HashMap<String, String>();
		List<Image> Images = new ArrayList<Image>();
		String msg = "Files uploaded successfully";
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = fileUpload.parseRequest(request);
				if (items != null) {
					Iterator<FileItem> iter = items.iterator();
					/*
					 * Return true if the instance represents a simple form field. Return false if
					 * it represents an uploaded file.
					 */
					while (iter.hasNext()) {
						final FileItem item = iter.next();
						final String itemName = item.getName();// related to file i.e. name of file
						final String fieldName = item.getFieldName();// key of form-data
						final String fieldValue = item.getString();// value of form-data
						if (item.isFormField()) {
							hm.put(fieldName, fieldValue);
							//System.out.println("Field Name: " + fieldName + ", Field Value: " + fieldValue);
						} else {
                            // item is image or pdf
							//System.out.println(fieldName);
							if (itemName.endsWith("png") || itemName.endsWith("jpg") || itemName.endsWith("jpeg")) {
								fileLocation = uploadFileToDirectory(item, itemName, IMAGE_UPLOAD_PATH);
								Image img=new Image();
								img.setImagePath(fileLocation);
//								System.out.println(fieldName);
//								System.out.println(fileLocation);
								if (fieldName.equals("primaryImage")) {
									img.setIsPrimaryImage(true);
								}
								Images.add(img);
								System.out.println(img.getImagePath());
								System.out.println(img.getIsPrimaryImage());
							}
							else if(itemName.endsWith("pdf") || itemName.endsWith("doc") || itemName.endsWith("txt")) {
								fileLocation = uploadFileToDirectory(item, itemName, DOC_UPLOAD_PATH);
								product.setPdfPath(fileLocation);
							}
						}
					}
				}
				//System.out.println(Images.size());
				createProductFromFormFields(hm);
				
				Iterator<Image> it = Images.iterator();
				while (it.hasNext()) {
					Image itt=(Image)it.next();
					System.out.println( itt.getImagePath());
					System.out.println(itt.getIsPrimaryImage());

					product.getImages().add((Image) itt);
					
				}System.out.println(product.getImages().size());
				imageService.saveImage(product.getImages());
				productRepository.save(product);
				System.out.println(product);
				
			} catch (FileUploadException e) {
				code = 404;
				msg = e.getMessage();
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				code = 404;
				msg = e.getMessage();
			}
		}

		return product;
	}

	public void createProductFromFormFields(HashMap<String, String> hm) throws Exception {

		for (Map.Entry m : hm.entrySet()) {
			switch ((String) m.getKey()) {
			case "prodCode":
				product.setProdCode((String) m.getValue());
				break;
			case "prodName":
				product.setProdName((String) m.getValue());
				break;
			case "shortDes":
				product.setShortDes((String) m.getValue());
				break;
			case "longDesc":
				product.setLongDesc((String) m.getValue());
				break;
			case "prodLength":
				product.setProdLength(Double.parseDouble((String)m.getValue()) );
				break;
			case "prodBreadth":
				product.setProdBreadth(Double.parseDouble((String)m.getValue()));
				break;
			case "prodHeight":
				product.setProdHeight(Double.parseDouble((String)m.getValue()));
				break;
			case "MRP":
				product.setMRP(Double.parseDouble((String)m.getValue()));
				break;
			case "SSP":
				product.setSSP(Double.parseDouble((String)m.getValue()));
				break;
			case "YMP":
				product.setYMP(Double.parseDouble((String)m.getValue()));
				break;
			case "category":
				product.setProdName((String) m.getValue());
				break;
			case "prodColor":
				product.setProdColor((String) m.getValue());
			case "prodWeight":
				product.setProdWeight(Double.parseDouble((String)m.getValue()));
			case "prodBrand":
				product.setProdName((String) m.getValue());
				break;
			case "prodStatus":
				product.setProdName((String) m.getValue());
				break;
			case "sellerId":
				seller=sellerService.findBySellerId(Long.parseLong((String)m.getValue()));
				product.setSeller(seller);
			}
		}
		UUID uniqueKey = UUID.randomUUID();
		product.setProdId(String.valueOf(uniqueKey));
	}

	// method to upload image to a particular directory
	public String uploadFileToDirectory(FileItem item, String fileName, String directoryPath) {
		File file;
		String fileLocation=null;
		try {
			if (fileName.lastIndexOf("\\") >= 0) {
				fileLocation = directoryPath + fileName.substring(fileName.lastIndexOf("\\"));
				file = new File(fileLocation);
			} else {
				fileLocation = directoryPath + fileName.substring(fileName.lastIndexOf("\\") + 1);
				file = new File(fileLocation);
			}
			item.write(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return fileLocation;
		}
	}

}
