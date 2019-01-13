package by.training.zorich.dal.builder_layer;

import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;

public interface DalBuilder {
    DAOFactory build() throws DAOException;
}
