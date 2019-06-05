package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.models.Taco;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	public TacoResource toResource(Taco taco) {
		return createResourceWithId(taco.getId(), taco);
		}
	
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco);
		}

}
