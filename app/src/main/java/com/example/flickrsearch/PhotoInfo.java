package com.example.flickrsearch;

public class PhotoInfo {
    private Info photo;

    public Info getInfo() {
        return photo;
    }

    public PhotoInfo(Info photo) {
        this.photo = photo;
    }

    public class Info {
        private Title title;
        private Description description;
        private Owner owner;

        public Info(Title title, Description description, Owner owner) {
            this.title = title;
            this.description = description;
            this.owner = owner;
        }

        public Title getTitle() {
            return title;
        }

        public Description getDescription() {
            return description;
        }

        public Owner getOwner() {
            return owner;
        }

        public class Title {
            private String _content;

            public String get_content() {
                return _content;
            }

            public Title(String _content) {
                this._content = _content;
            }
        }
        public class Description {
            private String _content;

            public String get_content() {
                return _content;
            }

            public Description(String _content) {
                this._content = _content;
            }
        }
        public class Owner {
            private String userName;
            private String realName;

            public Owner(String userName, String realName) {
                this.userName = userName;
                this.realName = realName;
            }

            public String getUserName() {
                return userName;
            }

            public String getRealName() {
                return realName;
            }
        }
    }
}
