package com.aurionpro.loan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.loan.dto.EmiRequestDto;
import com.aurionpro.loan.dto.EmiResponseDto;
import com.aurionpro.loan.dto.EnquiryRequestDto;
import com.aurionpro.loan.dto.EnquiryResponseDto;
import com.aurionpro.loan.dto.LoanRequestDto;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.LoanSchemeResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.PageResponseDto;
import com.aurionpro.loan.dto.RequiredDocumentsRequestDto;
import com.aurionpro.loan.dto.RequiredDocumentsResponseDto;
import com.aurionpro.loan.dto.UserAdminViewResponse;
import com.aurionpro.loan.dto.UserRequestDto;
import com.aurionpro.loan.dto.UserResponseDto;
import com.aurionpro.loan.entity.Emi;
import com.aurionpro.loan.entity.Enquiry;
import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.LoanScheme;
import com.aurionpro.loan.entity.RequiredDocuments;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.exceptions.UserException;
import com.aurionpro.loan.repository.EmiRepository;
import com.aurionpro.loan.repository.EnquiryRepository;
import com.aurionpro.loan.repository.LoanRequestRepository;
import com.aurionpro.loan.repository.LoanSchemeRepository;
import com.aurionpro.loan.repository.RequiredDocumentsRepository;
import com.aurionpro.loan.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.transaction.Transactional;
@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private LoanSchemeRepository loanSchemeRepo;
	
	
	
	
	@Autowired
	private LoanRequestRepository loanRequestRepo;
	
	@Autowired
	private EmiRepository emiRepo;
	
	@Autowired
	private EnquiryRepository queryRepo;
	
	
	

	@Override
	public UserResponseDto addUser(UserRequestDto userRequestDto) {
		System.out.println("UserRequestDto: " + userRequestDto);
		User user = mapper.map(userRequestDto, User.class);
		User dbUser = userRepo.save(user);
		
		UserResponseDto userResponseDto = mapper.map(dbUser, UserResponseDto.class);
		
		return userResponseDto;
	}

	@Override
	public PageResponseDto<LoanSchemeResponseDto> getAllLoanScheme(int pageSize, int pageNumber) {
		
		 Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize); 
		 
	     Page<LoanScheme> dbUserPage = loanSchemeRepo.findAll(pageable); 
	      
	     Page<LoanSchemeResponseDto> loanSchemeResponseDto = dbUserPage.map(loanScheme -> mapper.map(loanScheme, LoanSchemeResponseDto.class)); 
	 
	   
	    PageResponseDto<LoanSchemeResponseDto> responsePageUser = new PageResponseDto<>(); 
	    responsePageUser.setTotalElements(loanSchemeResponseDto.getTotalElements());
	    responsePageUser.setTotalPages(loanSchemeResponseDto.getTotalPages());
	    responsePageUser.setPageSize(loanSchemeResponseDto.getSize());
		responsePageUser.setLastPage(loanSchemeResponseDto.isLast());
		responsePageUser.setContents(loanSchemeResponseDto.getContent());
		
		System.out.println("dbUserPage: " + dbUserPage.getContent());
		System.out.println("loanSchemeResponseDto: " + loanSchemeResponseDto.getContent());

	 
	    return responsePageUser;
	}
	
	@Override
	public LoanResponseDto applyLoan(LoanRequestDto loanRequestDto) {
	
	    LoanRequest loanRequest = mapper.map(loanRequestDto, LoanRequest.class);

	   
	    double loanAmount = loanRequest.getLoanamount();
	    int timeInYears = loanRequest.getTime();
	   
	    
	
		  LoanScheme loanScheme =
		  loanSchemeRepo.findById(loanRequestDto.getLoanscheme().getId())
		  .orElseThrow(() -> new
		  IllegalArgumentException("Invalid loan scheme ID provided."));
		  loanRequest.setLoanscheme(loanScheme);
		
	    
	   


	    if (loanScheme == null) {
	        throw new IllegalArgumentException("Loan scheme must be provided.");
	    }
	    double interestRate = loanScheme.getInterest();

	    double simpleInterest = (loanAmount * interestRate * timeInYears) / 100;

	    double totalRepayAmount = loanAmount + simpleInterest;

	    double monthlyRepayment = totalRepayAmount / (timeInYears * 12);

	    loanRequest.setRepayamount(totalRepayAmount);

	    
	    LoanRequest dbLoan = loanRequestRepo.save(loanRequest);

	    // Map the persisted LoanRequest entity to the response DTO
	    LoanResponseDto loanResponseDto = mapper.map(dbLoan, LoanResponseDto.class);

	    // Add calculated values to the response DTO
	    loanResponseDto.setSimpleInterest(simpleInterest);
	    loanResponseDto.setTotalRepayAmount(totalRepayAmount);
	    loanResponseDto.setMonthlyRepayment(monthlyRepayment);

	    return loanResponseDto;
	}


	@Override
	public EmiResponseDto emiPayment(EmiRequestDto emiRequestDto) {
		
		Emi emi = mapper.map(emiRequestDto, Emi.class);
		Emi dbEmi = emiRepo.save(emi);
		
		EmiResponseDto emiResponseDto = mapper.map(dbEmi, EmiResponseDto.class);
		
		return emiResponseDto; 
	}

	@Override
	public PageResponseDto<EmiResponseDto> getAllEmis(int pageSize, int pageNumber) {
		
		Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize); 
		 
	     Page<Emi> dbEmiPage = emiRepo.findAll(pageable); 
	      
	     Page<EmiResponseDto> emiResponseDto = dbEmiPage.map(emi -> mapper.map(emi, EmiResponseDto.class)); 
	 
	   
	    PageResponseDto<EmiResponseDto> responsePageEmi = new PageResponseDto<>(); 
	    responsePageEmi.setTotalElements(emiResponseDto.getTotalElements());
	    responsePageEmi.setTotalPages(emiResponseDto.getTotalPages());
	    responsePageEmi.setPageSize(emiResponseDto.getSize());
	    responsePageEmi.setLastPage(emiResponseDto.isLast());
	    responsePageEmi.setContents(emiResponseDto.getContent());
		
		System.out.println("dbUserPage: " + dbEmiPage.getContent());
		System.out.println("loanSchemeResponseDto: " + emiResponseDto.getContent());

	 
	    return responsePageEmi;
	}

	@Override
	public EnquiryResponseDto submitQueries(EnquiryRequestDto enquiryRequestDto) {

		Enquiry query = mapper.map(enquiryRequestDto, Enquiry.class);
		Enquiry dbQuery = queryRepo.save(query);
		
		EnquiryResponseDto queryResponseDto = mapper.map(dbQuery, EnquiryResponseDto.class);
		
		return queryResponseDto;
	}

	@Override
	public PageResponseDto<EnquiryResponseDto> getAllQueries(int pageSize, int pageNumber) {
		
		Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize); 
		 
	     Page<Enquiry> dbQueryPage = queryRepo.findAll(pageable); 
	      
	     Page<EnquiryResponseDto> queryResponseDto = dbQueryPage.map(query -> mapper.map(query, EnquiryResponseDto.class)); 
	 
	   
	    PageResponseDto<EnquiryResponseDto> responsePageQuery = new PageResponseDto<>(); 
	    responsePageQuery.setTotalElements(queryResponseDto.getTotalElements());
	    responsePageQuery.setTotalPages(queryResponseDto.getTotalPages());
	    responsePageQuery.setPageSize(queryResponseDto.getSize());
	    responsePageQuery.setLastPage(queryResponseDto.isLast());
	    responsePageQuery.setContents(queryResponseDto.getContent());
		

	    return responsePageQuery;
	}
	  @Autowired
	    private Cloudinary cloudinary;

	    @Autowired
	    private RequiredDocumentsRepository requiredDocumentRepository;


	    @Transactional
	    @Override
	    public RequiredDocumentsResponseDto uploadFile(MultipartFile file) throws IOException {
	        RequiredDocumentsResponseDto response = new RequiredDocumentsResponseDto();

	        
	        if (file == null || file.isEmpty()) {
	            response.setStatus("FAILURE");
	            response.setMessage("No file provided.");
	            return response;
	        }

	        try {
	          
	            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(),
	                    ObjectUtils.asMap("resource_type", "auto"));

	         
	            String documentOneLink = uploadResult.get("url").toString();

	          
	            LoanRequest loanRequest = new LoanRequest();
	           
	            loanRequest = loanRequestRepo.save(loanRequest);

	           
	            RequiredDocuments requiredDocuments = new RequiredDocuments();
	            requiredDocuments.setDocumentOneLink(documentOneLink);
	            requiredDocuments.setLoanrequest(loanRequest);  // Now we associate the LoanRequest

	         
	            requiredDocumentRepository.save(requiredDocuments);

	          
	            response.setDocumentId(requiredDocuments.getId());
	            response.setDocumentOneLink(documentOneLink);
	            response.setStatus("SUCCESS");
	            response.setMessage("File uploaded and saved successfully.");

	        } catch (IOException e) {
	            // Handle the exception during file upload
	            response.setStatus("FAILURE");
	            response.setMessage("Error occurred while uploading the file: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return response;
	    }


		@Override
		public RequiredDocumentsResponseDto addFileToDatabase(RequiredDocumentsRequestDto requiredDocumentsRequestDto) {
			// TODO Auto-generated method stub
			return null;
		}
		
		

		@Override
		public PageResponse<UserAdminViewResponse> getAllUser(int pageSize, int pageNumber) {
			Pageable pageable =  PageRequest.of(pageNumber, pageSize);
			Page<User> Userpage =  userRepo.findAll(pageable);
			List<User> dbLoanRequest =  Userpage.getContent();	
			List<UserAdminViewResponse> users =  new ArrayList<>();
			dbLoanRequest.forEach((user)->{
				UserAdminViewResponse userDto = mapper.map(user, UserAdminViewResponse.class);
				users.add(userDto);
			});
			PageResponse<UserAdminViewResponse> response =  new PageResponse<UserAdminViewResponse>(); 
			response.setContents(users);
			response.setLastPage(Userpage.isLast());
			response.setPageSize(Userpage.getSize());
			response.setTotalElements(Userpage.getTotalElements());
			response.setTotalPages(Userpage.getTotalPages());

			return response;
		}

		@Override
		public PageResponse<UserAdminViewResponse> getUserByFirstName(int pageSize, int pageNumber, String firstName) {
			Pageable pageable =  PageRequest.of(pageNumber, pageSize);
			Page<User> Userpage =  userRepo.findByFirstName(pageable,firstName);
			List<User> dbLoanRequest =  Userpage.getContent();	
			List<UserAdminViewResponse> users =  new ArrayList<>();
			dbLoanRequest.forEach((user)->{
				UserAdminViewResponse userDto = mapper.map(user, UserAdminViewResponse.class);
				users.add(userDto);
			});
			PageResponse<UserAdminViewResponse> response =  new PageResponse<UserAdminViewResponse>(); 
			response.setContents(users);
			response.setLastPage(Userpage.isLast());
			response.setPageSize(Userpage.getSize());
			response.setTotalElements(Userpage.getTotalElements());
			response.setTotalPages(Userpage.getTotalPages());

			return response;
		}

		@Override
		public UserAdminViewResponse getUserByEmail(String email) {
			User user =  userRepo.findByEmail(email);
			if(user ==  null)
				throw new UserException("Cannot found the User with emial: "+email);
			
			return mapper.map(user, UserAdminViewResponse.class);
		}

		@Override
		public UserAdminViewResponse getUserById(int Id) {
			Optional<User> user =  userRepo.findById(Id);
			if(user.isEmpty())
				throw new UserException("Cannot found the User with id: "+Id);
			User dbUser =  user.get();
			return mapper.map(user, UserAdminViewResponse.class);
		}

		
}
