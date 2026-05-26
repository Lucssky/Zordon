CREATE TABLE `jogos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL,
  `dificuldade` enum('FACIL','MEDIO','DIFICIL') DEFAULT NULL,
  `ativio` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `pontos` int(11) DEFAULT 0,
  `vitorias` int(11) DEFAULT 0,
  `derrotas` int(11) DEFAULT 0,
  PRIMARY KEY (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE partidas (
id INT AUTO_INCREMENT PRIMARY KEY,
usuario_id INT NOT NULL,
jogo_id INT NOT NULL,
pontuacao INT DEFAULT 0,
resultado ENUM('VITORIA','DERROTA'),
data_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;