package sample;

public class TerrainType {

        private boolean hills;
        private boolean flat;
        private boolean mountain;

        public TerrainType(boolean hills, boolean flat, boolean mountain)
        {
            this.hills = hills;
            this.flat = flat;
            this.mountain = mountain;
        }
        public int getAttackBonus()
        {
            if(hills)
            {
                return -10;
            }
            else if(flat)
            {
                return 10;
            }
            else
            {
                return -15;
            }
        }
        public int getDefenseBonus()
        {
            if(hills)
            {
                return 10;
            }
            else if(flat)
            {
                return -10;
            }
            else
            {
                return 15;
            }
        }
    }

