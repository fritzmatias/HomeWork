package fritzmatias.core.model;

public enum SpatialSystemStateType {
	TrianguloEncierraOrigen("lluvia"),TrianguloVacio("normal"), RectaConPunto("sequia"), RectaSinPunto("Optimo"), indefinido("Sin calcular");

	private String formalStatusName;
	
	SpatialSystemStateType(String nombre){
		this.formalStatusName=nombre;
	}
	
	public String getFormalStatusName() {
		return formalStatusName;
	}
	
}
