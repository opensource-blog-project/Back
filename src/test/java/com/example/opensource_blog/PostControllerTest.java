package com.example.opensource_blog;

import com.example.opensource_blog.controller.PostController;
import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import com.example.opensource_blog.dto.request.PostRequestDTO;
import com.example.opensource_blog.dto.response.PostResponseDTO;
import com.example.opensource_blog.service.hashtag.HashTageService;
import com.example.opensource_blog.service.hashtag.PostHashTageService;
import com.example.opensource_blog.service.post.PostService;
import com.example.opensource_blog.jwt.TokenProvider;
import com.example.opensource_blog.service.user.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostService postService;

	@MockBean
	private HashTageService hashTageService;

	@MockBean
	private PostHashTageService postHashTageService;

	@MockBean
	private TokenProvider tokenProvider;

	@Mock
	private UserInfo userInfo;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreatePost() throws Exception {
		// given
		PostRequestDTO postRequestDTO = new PostRequestDTO();
		postRequestDTO.setTitle("Title");
		postRequestDTO.setContent("Content");
		postRequestDTO.setRestaurant("Restaurant");

		MockMultipartFile postFile = new MockMultipartFile(
				"post",  // @RequestPart("post")와 일치해야 하는 이름
				"",
				"application/json",
				objectMapper.writeValueAsString(postRequestDTO).getBytes()
		);

		UserAccount user = new UserAccount("test_user","test_password","test_nickname");

		Post mockPost = new Post();  // 모킹된 Post 객체 생성
		mockPost.setPostId(1);  // 임의의 ID 설정
		mockPost.setTitle(postRequestDTO.getTitle());
		mockPost.setContent(postRequestDTO.getContent());
		mockPost.setRestaurant(postRequestDTO.getRestaurant());
		mockPost.setUser(user);

		Mockito.when(postService.createPost(any(), any(), any())).thenReturn(mockPost);

		// when & then
		mockMvc.perform(multipart("/posts/create")
						.file(postFile)
						.file("images", new byte[0])  // 빈 파일로 예시
						.with(csrf())
						.with(user("test_user").roles("USER").password("password")))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("Title"))
				.andExpect(jsonPath("$.content").value("Content"));

		verify(postService, times(1)).createPost(any(), any(), any());
	}

}
