package q2;

public class Greeter{
   /**
      Constructs a Greeter object that can greet a person or 
      entity.
      @param aName the name of the person or entity who should
      be addressed in the greetings.
   */
   public Greeter(String aName){
      name = aName;
   }

   /**
      Greet with a "Hello" message.
      @return a message containing "Hello" and the name of
      the greeted person or entity.
   */
   public String sayHello(){
      return "Hello, " + name + "!";
   }
   
   /**
   	Takes the Greeter parameter and replaces this name with the other.
   	@param other Greeter object
    */
   public void swapNames(Greeter other) {
	   this.name = other.name;
   }
   
   /**
  	Returns new Greeter object with string qualifier + this object's name
  	@param qualifier string parameter
  	@return Greeter object
   */
   public Greeter createQualifiedGreeter (String qualifier) {
	   return new Greeter(qualifier + " " + this.name);
   }

   private String name;
}
