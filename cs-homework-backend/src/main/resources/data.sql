INSERT INTO Question
(CustomerId, Title, Content, RegDate)
VALUES
('Customer1', '운영자들은 봐라', '아주 똥망겜 이게 게임이냐??', NOW());

INSERT INTO Counselor
(Id, Name, Pwd, RegDate)
VALUES
('Counselor1','김민지','$2a$10$349s6GTHrb7BzO1e47cRbezdEXijKdrwkitaC44osjYdbFRP2P86y', NOW()),
('Counselor2','왕칠순','$2a$10$349s6GTHrb7BzO1e47cRbezdEXijKdrwkitaC44osjYdbFRP2P86y', NOW())