package org.fsp.tp5venteauxenchere.client;

import org.fsp.tp5venteauxenchere.interface_client.Participant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ParticipantImpl extends UnicastRemoteObject implements Participant {

    private IHMParticipant ihm;

    protected ParticipantImpl() throws RemoteException {
    }
}
