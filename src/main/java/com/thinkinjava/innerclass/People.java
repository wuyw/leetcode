package com.thinkinjava.innerclass;

class People {
    public People(){

    }
}



class Man {
    public Man(){

    }

    public People getWoman(){

        /**
         * 局部内部类
         */
        class Woman extends People{
            public Woman(){

            }
        }

        return new Woman();
    }
}


