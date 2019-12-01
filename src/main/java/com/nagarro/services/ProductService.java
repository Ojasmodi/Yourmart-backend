package com.nagarro.services;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static final String IMAGE_UPLOAD_PATH = "C:\\Users\\ojasmodi\\Documents\\workspace-spring-tool-suite-4-4.4.0.RELEASE\\YourMart\\src\\main\\webapp\\images\\";
	private static final String DOC_UPLOAD_PATH = "C:\\Users\\ojasmodi\\Documents\\workspace-spring-tool-suite-4-4.4.0.RELEASE\\YourMart\\src\\main\\webapp\\docs\\";

	public Product findByProductId(String id) {
		Product product = productRepository.findById(id).orElse(null);
		return product;
	}

	public Product addProduct(HttpServletRequest request) {

		String fileLocation;
		HashMap<String, String> hm = new HashMap<String, String>();
		Set<Image> Images = new HashSet<Image>();
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = fileUpload.parseRequest(request);
				if (items != null) {
					Iterator<FileItem> iter = items.iterator();

					while (iter.hasNext()) {
						final FileItem item = iter.next();
						final String itemName = item.getName();// related to file i.e. name of file
						final String fieldName = item.getFieldName();// key of form-data
						final String fieldValue = item.getString();// value of form-data
						if (item.isFormField()) {
							hm.put(fieldName, fieldValue);
						} else {
							// item is image or pdf
							if (itemName.endsWith("png") || itemName.endsWith("jpg") || itemName.endsWith("jpeg")) {
								fileLocation = uploadFileToDirectory(item, itemName, IMAGE_UPLOAD_PATH);
								Image img = new Image();
								img.setImagePath(fileLocation);
								if (fieldName.equals("primaryImage")) {
									img.setIsPrimaryImage(true);
								}
								Images.add(img);
							} else if (itemName.endsWith("pdf") || itemName.endsWith("doc")
									|| itemName.endsWith("txt")) {
								fileLocation = uploadFileToDirectory(item, itemName, DOC_UPLOAD_PATH);
								product.setPdfPath(fileLocation);
							}
						}
					}
				}
				createProductFromFormFields(hm);

				product.setImages(Images);
				// product.setImages((Set<Image>)Images);

				product.setSeller(seller);
				productRepository.save(product);
				Iterator<Image> it = Images.iterator();
				while (it.hasNext()) {
					Image img = (Image) it.next();
					img.setProduct(product);
					product.getImages().add(img);
				}
				imageService.saveImage(product.getImages());
				// Set<Product> products=new HashSet<>();
				// products.add(product);
				// seller.setProducts(products);

//				seller.getProducts().add(product);
				sellerService.addSeller(seller);

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
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
				product.setProdLength(Double.parseDouble((String) m.getValue()));
				break;
			case "prodBreadth":
				product.setProdBreadth(Double.parseDouble((String) m.getValue()));
				break;
			case "prodHeight":
				product.setProdHeight(Double.parseDouble((String) m.getValue()));
				break;
			case "mrp":
				product.setMRP(Double.parseDouble((String) m.getValue()));
				break;
			case "ssp":
				product.setSSP(Double.parseDouble((String) m.getValue()));
				break;
			case "ymp":
				product.setYMP(Double.parseDouble((String) m.getValue()));
				break;
			case "category":
				product.setCategory((String) m.getValue());
				break;
			case "prodColor":
				product.setProdColor((String) m.getValue());
			case "prodWeight":
				product.setProdWeight(Double.parseDouble((String) m.getValue()));
			case "prodBrand":
				product.setProdBrand((String) m.getValue());
				break;
			case "sellerId":
				seller = sellerService.findBySellerId(Long.parseLong((String) m.getValue()));
				System.out.println(String.valueOf(seller));
				// product.setSeller(seller);
			}
		}
		product.setProdStatus("NEW");
		product.setCreatedOn(new Date());
		product.setUpdatedOn(new Date());
		// System.out.println(product);
		product.setProdId(String.valueOf(UUID.randomUUID()));
	}

	// method to upload image to a particular directory
	public String uploadFileToDirectory(FileItem item, String fileName, String directoryPath) {
		File file;
		String fileLocation = null;
		try {
			if (fileName.lastIndexOf("\\") >= 0) {
				fileLocation = directoryPath + Math.random() + fileName.substring(fileName.lastIndexOf("\\"));
			} else {
				fileLocation = directoryPath + Math.random() + fileName.substring(fileName.lastIndexOf("\\") + 1);
			}
			file = new File(fileLocation);
			item.write(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return fileLocation;
		}
	}

	public List<Product> getAllProducts() throws Exception {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	public Product getProductByProductId(String id) throws Exception {
		Product product = productRepository.findById(id).orElse(null);
		return product;
	}

	public Product updateOtherdetailsOfProduct(Product currentProduct, long sId) throws Exception {
//		currentProduct.setUpdatedOn(new Date());
		Seller seller = sellerService.findBySellerId(sId);
		currentProduct.setSeller(seller);
		Product product = productRepository.save(currentProduct);

		seller.getProducts().add(product);
		sellerService.addSeller(seller);
		return product;
	}

	@Transactional
	public boolean updateStatusOfProduct(Product currentProduct) {
		productRepository.updateStatusOfProduct(currentProduct.getProdId(), currentProduct.getProdStatus());
		return true;

	}

	public void saveProduct(Product product2) {
		productRepository.save(product2);
		
	}

}
