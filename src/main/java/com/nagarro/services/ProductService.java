package com.nagarro.services;

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
import com.nagarro.utils.Constants;
import com.nagarro.utils.FileUploader;

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
	
	@Autowired
	FileUploader fileupload;

	// method to product to find by product Id
	public Product findByProductId(String id) {
		Product product = productRepository.findById(id).orElse(null);
		return product;
	}

	// method to add new product
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
								fileLocation = fileupload.uploadFileToDirectory(item, itemName, Constants.IMAGE_UPLOAD_PATH);
								Image img = new Image();
								img.setImagePath(fileLocation);
								if (fieldName.equals("primaryImage")) {
									img.setIsPrimaryImage(true);
								}
								Images.add(img);
							} else if (itemName.endsWith("pdf") || itemName.endsWith("doc")
									|| itemName.endsWith("txt")) {
								fileLocation = fileupload.uploadFileToDirectory(item, itemName, Constants.DOC_UPLOAD_PATH);
								product.setPdfPath(fileLocation);
							}
						}
					}
				}
				createProductFromFormFields(hm);
				product.setImages(Images);
				product.setSeller(seller);
				productRepository.save(product);
				Iterator<Image> it = Images.iterator();
				while (it.hasNext()) {
					Image img = (Image) it.next();
					img.setProduct(product);
					product.getImages().add(img);
				}
				imageService.saveImage(product.getImages());
				sellerService.addSeller(seller);

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	// method to create a new product from hashmap
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
				break;
			case "prodWeight":
				product.setProdWeight(Double.parseDouble((String) m.getValue()));
				break;
			case "prodBrand":
				product.setProdBrand((String) m.getValue());
				break;
			case "sellerId":
				seller = sellerService.findBySellerId(Long.parseLong((String) m.getValue()));
			}
		}
		product.setProdStatus("NEW");
		product.setCreatedOn(new Date());
		product.setUpdatedOn(new Date());
		product.setProdId(String.valueOf(UUID.randomUUID()));
	}


	//method to get all products
	public List<Product> getAllProducts() throws Exception {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	// method to get product by product Id
	public Product getProductByProductId(String id) throws Exception {
		Product product = productRepository.findById(id).orElse(null);
		return product;
	}

	// method to update other details of the project
	public Product updateOtherdetailsOfProduct(Product currentProduct, long sId) throws Exception {
		currentProduct.setUpdatedOn(new Date());
		Seller seller = sellerService.findBySellerId(sId);
		currentProduct.setSeller(seller);
		Product product = productRepository.save(currentProduct);

		seller.getProducts().add(product);
		sellerService.addSeller(seller);
		return product;
	}

	//method to update status of the product
	@Transactional
	public boolean updateStatusOfProduct(Product currentProduct) {
		currentProduct.setUpdatedOn(new Date());
		productRepository.updateStatusOfProduct(currentProduct.getProdId(), currentProduct.getProdStatus());
		return true;

	}

	// method to save/update new product
	public void saveProduct(Product product2) {
		productRepository.save(product2);
	}

	// method of update primary Image or pdf document of the existing product
	@Transactional
	public boolean updateFilesOfProduct(HttpServletRequest request, String prodId) {
		String fileLocation;
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = fileUpload.parseRequest(request);
				if (items != null) {
					Iterator<FileItem> iter = items.iterator();

					while (iter.hasNext()) {
						final FileItem item = iter.next();
						if(item.getFieldName().equals("primaryImage")) {
							fileLocation = fileupload.uploadFileToDirectory(item, item.getName(), Constants.IMAGE_UPLOAD_PATH);
							long imageID=0;
							product=getProductByProductId(prodId);
							product.setUpdatedOn(new Date());
							Set<Image> images=product.getImages();
							for(Image i:images) {
								if(i.getIsPrimaryImage()) {
                                    imageID=i.getImageId();
								}
							}
							imageService.updatePathOfPrimaryImage(fileLocation,imageID);
							
						}
						else {
							fileLocation = fileupload.uploadFileToDirectory(item, item.getName(), Constants.DOC_UPLOAD_PATH);
							productRepository.updatePdfPath(fileLocation,prodId);
						}
					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	
// method to add images to the existing product
	public boolean addNewImagesOfProduct(HttpServletRequest request, long sellerId,String prodId) throws Exception {
		String fileLocation;
		product=findByProductId(prodId);
		seller=sellerService.findBySellerId(sellerId);
		FileItemFactory factory = new DiskFileItemFactory();
		Set<Image> images=new HashSet<>();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			if (items != null) {
				Iterator<FileItem> iter = items.iterator();

				while (iter.hasNext()) {
					final FileItem item = iter.next();
						fileLocation = fileupload.uploadFileToDirectory(item, item.getName(), Constants.IMAGE_UPLOAD_PATH);
						Image img=new Image();
						img.setImagePath(fileLocation);
						images.add(img);
				}
			}
		product.setImages(images);
		product.setSeller(seller);
		product.setUpdatedOn(new Date());
		productRepository.save(product);
		Iterator<Image> it = images.iterator();
		while (it.hasNext()) {
			Image img = (Image) it.next();
			img.setProduct(product);
			product.getImages().add(img);
		}
		imageService.saveImage(product.getImages());
		sellerService.addSeller(seller);

		} catch (FileUploadException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	return true;
	}

}
