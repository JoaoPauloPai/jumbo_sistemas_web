package br.com.jumbo;



//@SpringBootTest(classes =  JumboSistemasWebApplication.class)
public class JumboSistemasWebApplicationTests {
	/**
	@Autowired
	private AcessoService acessoService;
	
	//  @Autowired
	//  private AcessoRepository acessoRepository;
	
	

	@Test
	public void testCadastraAcesso() {
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");
		
		acessoService.save(acesso);
		
	}

*/
}
