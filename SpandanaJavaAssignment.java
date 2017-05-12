/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spandanajavaassignment;

class Person
{
    boolean pyzamaWorn;
    boolean shoeWorn;
    boolean socksWorn;
    boolean pantsWorn;
    boolean headWearWorn;
    boolean jacketWorn;
    boolean shirtWorn;
    boolean leftHouse;

    Person() {
        pyzamaWorn = true;
        shoeWorn = false;
        socksWorn = false;
        pantsWorn = false;
        headWearWorn = false;
        leftHouse = false;
        jacketWorn = false;
        shirtWorn = false;
    }
}
/**
 *
 * @author bspandana
 */
class Solution
{
    String input;
    String[] hotResp = {"sandals",
                            "sun visor",
                            "fail",
                            "t-shirt",
                            "fail",
                            "shorts",
                            "leaving house",
                            "Removing PJs"}; 
    String[] coldResp = {"boots",
                            "hat",
                            "socks",
                            "shirt",
                            "jacket",
                            "pants",
                            "leaving house",
                            "Removing PJs"}; 
    
    Solution(String i) {
        input = i;
    }
    String[] parseCmds(String input) {
        return input.split("\\s*(,\\s)\\s*");
    }
    public boolean printClothing(int clothing, String weather) {
        if (clothing < 0 || clothing > 7) return false;
        if (weather.compareTo("HOT") == 0) {
            if(hotResp[clothing].compareTo("fail") == 0) return false;
            System.out.print(hotResp[clothing]);
        } else if (weather.compareTo("COLD") == 0) {
            if(coldResp[clothing].compareTo("fail") == 0) return false;
            System.out.print(coldResp[clothing]);
        } else {
            return false;
        }
        return true;
    }
    public boolean dressUpPerson(Person p, String cmd, String weather) {
        if (p.leftHouse == true) {
            // cant dress up person after he left the house
            return false;
        }
        if (cmd.compareTo("8") == 0) {
            if (p.pyzamaWorn == false ) {
                return false;
            }
            if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
            p.pyzamaWorn = false;
        } else {
            // By the time the person wears something else, the pyjama should
            // have been removed.
            if (p.pyzamaWorn == true) {
                // already wore a pyzama, cant wear it again
                return false;
            }
            
            if (cmd.compareTo("7") == 0) {
                // trying to leave home.
                
                // check if all items of clothing has been worn.
                if (weather.compareTo("COLD") == 0) {
                    if (p.headWearWorn  == false ||
                        p.shoeWorn      == false ||
                        p.socksWorn     == false ||
                        p.pantsWorn     == false ||
                        p.jacketWorn    == false ||
                        p.shirtWorn     == false) {
                        return false;
                    }
                } else {
                    if (p.headWearWorn  == false ||
                        p.shoeWorn      == false ||
                        p.pantsWorn     == false ||
                        p.shirtWorn     == false) {
                        return false;
                    }
                }
                
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.leftHouse = true;
            } else if (cmd.compareTo("6") == 0) {
                // trying to wear a pant.
                if (p.pantsWorn == true) {
                    // already wore a pant, cant wear it again
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.pantsWorn = true;
            } else if (cmd.compareTo("5") == 0) {
                // trying to wear a jacket.
                if (p.jacketWorn == true) {
                    // already wore a jacket, cant wear it again
                    return false;
                }
                if (p.shirtWorn == false) {
                    // if I'm trying to wear a jacket, I should have shirt
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.jacketWorn = true;
            } else if (cmd.compareTo("4") == 0) {
                // trying to wear a shirt.
                if (p.shirtWorn == true) {
                    // already wore a shirt, cant wear it again
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.shirtWorn = true;
            } else if (cmd.compareTo("3") == 0) {
                // trying to wear a sock.
                if (p.socksWorn == true) {
                    // already wore a socks, cant wear it again
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.socksWorn = true;
            } else if (cmd.compareTo("2") == 0) {
                // trying to wear a headwear.
                if (p.headWearWorn == true) {
                    // already wore a headwear, cant wear it again
                    return false;
                }
                if (p.shirtWorn == false) {
                    // if I'm trying to wear a headwear, I should have shirt
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.headWearWorn = true;
            } else if (cmd.compareTo("1") == 0) {
                // trying to wear a shoe.
                if (p.shoeWorn == true) {
                    // already wore a shoe, cant wear it again
                    return false;
                }
                if (p.pantsWorn == false) {
                    // if I'm trying to wear a shoe, I should have pants and socks
                    return false;
                }
                if ((weather.compareTo("COLD") == 0) && p.socksWorn == false)
                {
                    // if weather is cold, we should have worn socks before shoes.
                    return false;
                }
                if (!printClothing(Integer.parseInt(cmd) - 1, weather)) return false;
                p.shoeWorn = true;
            }
        }
        return true;
    }
    public void processInput()
    {
        // boolean hot;
        int indexOfSpace = input.indexOf(' ');
        String weather = input.substring(0, indexOfSpace);
        if ((weather.compareTo("HOT") != 0) && (weather.compareTo("COLD") != 0)) {
            System.out.println("fail");
            return;
        }
        String[] cmds = parseCmds(input.substring(indexOfSpace));
        int listOfCommands = cmds.length;
        Person p = new Person();
        for (String cmd : cmds) {
            cmd = cmd.trim();
            if (!dressUpPerson(p, cmd, weather)) {
                System.out.print("fail");
                return;
            }
            if (listOfCommands != 1) {
                System.out.print(", ");
            }
            listOfCommands--;
        }   
    }
}

/**
 *
 * @author bspandana
 */
public class SpandanaJavaAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Solution s;
        {
            s = new Solution("HOT 8, 6, 4, 2, 1, 7");
            s.processInput();
            System.out.println();
        }
        {
            s = new Solution("COLD 8, 6, 3, 4, 2, 5, 1, 7");
            s.processInput();
            System.out.println();
        }
        {
            s = new Solution("HOT 8, 6, 6");
            s.processInput();
            System.out.println();
        }
        {
            s = new Solution("HOT 8, 6, 3");
            s.processInput();
            System.out.println();
        }
        {
            s = new Solution("COLD 8, 6, 3, 4, 2, 5, 7");
            s.processInput();
            System.out.println();
        }
        {
            s = new Solution("HOT 8, 6, 4, 2, 7");
            s.processInput();
            System.out.println();
        }
    }
}
