-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 29 mai 2021 à 13:19
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_releve`
--

-- --------------------------------------------------------

--
-- Structure de la table `Ecu`
--

CREATE TABLE `Ecu` (
  `codeEcu` int(11) NOT NULL,
  `codeUe` int(11) NOT NULL,
  `libelleEcu` varchar(50) NOT NULL,
  `creditEcu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Etudiant`
--

CREATE TABLE `Etudiant` (
  `matricule` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Note`
--

CREATE TABLE `Note` (
  `codeNote` int(11) NOT NULL,
  `matricule` int(11) NOT NULL,
  `codeEcu` int(11) NOT NULL,
  `valeur` float NOT NULL,
  `dateDevoir` date NOT NULL,
  `session` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Parcours`
--

CREATE TABLE `Parcours` (
  `codeParcours` int(11) NOT NULL,
  `libelleParcours` varchar(50) NOT NULL,
  `diplomeParcours` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Parc_Sem`
--

CREATE TABLE `Parc_Sem` (
  `codeParcours` int(11) NOT NULL,
  `codeSemestre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Semestre`
--

CREATE TABLE `Semestre` (
  `codeSemestre` int(11) NOT NULL,
  `libelleSemestre` varchar(50) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Ue`
--

CREATE TABLE `Ue` (
  `codeUe` int(11) NOT NULL,
  `codeSemestre` int(11) NOT NULL,
  `libelleUe` varchar(50) NOT NULL,
  `creditUe` int(11) NOT NULL,
  `typeUe` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Ecu`
--
ALTER TABLE `Ecu`
  ADD PRIMARY KEY (`codeEcu`),
  ADD KEY `codeUe_FK` (`codeUe`);

--
-- Index pour la table `Etudiant`
--
ALTER TABLE `Etudiant`
  ADD PRIMARY KEY (`matricule`);

--
-- Index pour la table `Note`
--
ALTER TABLE `Note`
  ADD PRIMARY KEY (`codeNote`),
  ADD KEY `codeEcu_FK` (`codeEcu`),
  ADD KEY `matricule_FK` (`matricule`);

--
-- Index pour la table `Parcours`
--
ALTER TABLE `Parcours`
  ADD PRIMARY KEY (`codeParcours`);

--
-- Index pour la table `Parc_Sem`
--
ALTER TABLE `Parc_Sem`
  ADD KEY `codePacours_FK` (`codeParcours`),
  ADD KEY `codeSemestre` (`codeSemestre`);

--
-- Index pour la table `Semestre`
--
ALTER TABLE `Semestre`
  ADD PRIMARY KEY (`codeSemestre`);

--
-- Index pour la table `Ue`
--
ALTER TABLE `Ue`
  ADD PRIMARY KEY (`codeUe`),
  ADD KEY `codeSemestre_FK` (`codeSemestre`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Ecu`
--
ALTER TABLE `Ecu`
  MODIFY `codeEcu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Etudiant`
--
ALTER TABLE `Etudiant`
  MODIFY `matricule` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Note`
--
ALTER TABLE `Note`
  MODIFY `codeNote` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Parcours`
--
ALTER TABLE `Parcours`
  MODIFY `codeParcours` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Semestre`
--
ALTER TABLE `Semestre`
  MODIFY `codeSemestre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Ue`
--
ALTER TABLE `Ue`
  MODIFY `codeUe` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Ecu`
--
ALTER TABLE `Ecu`
  ADD CONSTRAINT `codeUe_FK` FOREIGN KEY (`codeUe`) REFERENCES `Ue` (`codeUe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Note`
--
ALTER TABLE `Note`
  ADD CONSTRAINT `codeEcu_FK` FOREIGN KEY (`codeEcu`) REFERENCES `Ecu` (`codeEcu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matricule_FK` FOREIGN KEY (`matricule`) REFERENCES `Etudiant` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Parc_Sem`
--
ALTER TABLE `Parc_Sem`
  ADD CONSTRAINT `codePacours_FK` FOREIGN KEY (`codeParcours`) REFERENCES `Parcours` (`codeParcours`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `codeSemestre` FOREIGN KEY (`codeSemestre`) REFERENCES `Semestre` (`codeSemestre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Ue`
--
ALTER TABLE `Ue`
  ADD CONSTRAINT `codeSemestre_FK` FOREIGN KEY (`codeSemestre`) REFERENCES `Semestre` (`codeSemestre`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
