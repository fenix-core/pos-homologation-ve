/************************************************************************************
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program. If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.pos.homologation.controller;

import org.compiere.util.CLogger;
import org.spin.pos.homologation.service.Service;
import org.spin.proto.pos.homologation.GetSystemInfoRequest;
import org.spin.proto.pos.homologation.Order;
import org.spin.proto.pos.homologation.PosHomologationServiceGrpc.PosHomologationServiceImplBase;
import org.spin.proto.pos.homologation.PrintTicketResponse;
import org.spin.proto.pos.homologation.ProcessReverseSalesWithoutPrintRequest;
import org.spin.proto.pos.homologation.ProcessWithoutPrintRequest;
import org.spin.proto.pos.homologation.SimulateProcessOrderRequest;
import org.spin.proto.pos.homologation.SimulateReverseSalesRequest;
import org.spin.proto.pos.homologation.SystemInfo;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class PosHomologationService extends PosHomologationServiceImplBase {

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(PosHomologationService.class);


	@Override
	public void getSystemInfo(GetSystemInfoRequest request, StreamObserver<SystemInfo> responseObserver) {
		try {
			SystemInfo.Builder builder = Service.getSystemInfo();
			responseObserver.onNext(builder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}


	@Override
	public void simulateProcessOrder(SimulateProcessOrderRequest request, StreamObserver<PrintTicketResponse> responseObserver) {
		try {
			PrintTicketResponse.Builder builder = Service.simulateProcessOrder(request);
			responseObserver.onNext(
				builder.build()
			);
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}


	@Override
	public void processWithoutPrint(ProcessWithoutPrintRequest request, StreamObserver<Order> responseObserver) {
		try {
			Order.Builder builder = Service.processWithoutPrint(request);
			responseObserver.onNext(
				builder.build()
			);
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}


	@Override
	public void simulateReverseSales(SimulateReverseSalesRequest request, StreamObserver<PrintTicketResponse> responseObserver) {
		try {
			PrintTicketResponse.Builder builder = PrintTicketResponse.newBuilder();
			responseObserver.onNext(
				builder.build()
			);
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}


	@Override
	public void processReverseSalesWithoutPrint(ProcessReverseSalesWithoutPrintRequest request, StreamObserver<Order> responseObserver) {
		try {
			Order.Builder builder = Order.newBuilder();
			responseObserver.onNext(
				builder.build()
			);
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

}
